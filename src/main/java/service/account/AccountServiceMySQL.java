package service.account;

public class AccountServiceMySQL implements AccountService {

    private AccountService accountService;

    public AccountServiceMySQL(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String getAccountData() {
        return null;
    }
}
