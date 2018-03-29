package service.account;

import database.DBConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import repository.account.AccountRepositoryMySQL;

import static org.junit.Assert.*;

public class AccountServiceMySQLTest {

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceMySQL(new AccountRepositoryMySQL(new DBConnectionFactory().getConnectionWrapper(true).getConnection()));
    }

    @Test
    public void insertAccount() {
        int before = (int) accountService.getAccountData().chars().filter(num -> num == '\n').count();
        accountService.insertAccount(100,1,false,0);
        int after = (int) accountService.getAccountData().chars().filter(num -> num == '\n').count();
        assertEquals(before+1, after);
    }

    @Test
    public void getAccountData() {
        assertEquals("100", accountService.getAccountData().split("\n")[0].split(" ")[1]);
    }

    @Test
    public void updateAccount() {
        accountService.updateAccount(2L,200,1,false,0);
        String amount = accountService.getAccountData().split(" ")[1];
        assertEquals(amount, "200");
    }

    @Test
    public void getAccount() {
        assertEquals(100, accountService.getAccount(2L).getResult().getAmount());
    }

    @Test
    public void delete() {
        int before = (int) accountService.getAccountData().chars().filter(num -> num == '\n').count();
        accountService.delete(1);
        int after = (int) accountService.getAccountData().chars().filter(num -> num == '\n').count();
        insertAccount();
        assertEquals(before-1, after);
    }
}