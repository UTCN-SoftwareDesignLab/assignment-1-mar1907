package service.transfer;

import factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import service.account.AccountService;

import static org.junit.Assert.*;

public class TransferServiceMySQLTest {

    private TransferService transferService;
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        transferService = ServiceFactory.instance().getTransferService();
        accountService = ServiceFactory.instance().getAccountService();
    }

    @Test
    public void transfer() {
        accountService.insertAccount(10,1,false,0);
        accountService.insertAccount(10,1,false,0);
        accountService.insertAccount(10,1,false,0);
        int famount1 = accountService.getAccount(2).getResult().getAmount();
        int famount2 = accountService.getAccount(3).getResult().getAmount();
        transferService.transfer(2,3,10);
        int amount1 = accountService.getAccount(2).getResult().getAmount();
        int amount2 = accountService.getAccount(3).getResult().getAmount();
        assertEquals(famount1-10,amount1);
        assertEquals(famount2+10,amount2);
    }
}