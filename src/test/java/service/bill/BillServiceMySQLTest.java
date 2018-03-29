package service.bill;

import database.DBConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import repository.account.AccountRepositoryMySQL;
import repository.bill.BillRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceMySQL;

import static org.junit.Assert.*;

public class BillServiceMySQLTest {

    private BillService billService;
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        billService = new BillServiceMySQL(new AccountRepositoryMySQL(new DBConnectionFactory().getConnectionWrapper(true).getConnection()),
                new BillRepositoryMySQL(new DBConnectionFactory().getConnectionWrapper(true).getConnection()));
        accountService = new AccountServiceMySQL(new AccountRepositoryMySQL(new DBConnectionFactory().getConnectionWrapper(true).getConnection()));
    }

    @Test
    public void payBill() {
        int amount = accountService.getAccount(2).getResult().getAmount();
        billService.payBill(2,10,"alabala");
        int newAmount = accountService.getAccount(2).getResult().getAmount();
        assertEquals(amount-10,newAmount);
    }
}