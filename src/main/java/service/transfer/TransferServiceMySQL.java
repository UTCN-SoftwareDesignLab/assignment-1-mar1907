package service.transfer;

import model.validation.Notification;
import repository.account.AccountRepository;

public class TransferServiceMySQL implements TransferService {

    private AccountRepository accountRepository;

    public TransferServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Notification<Boolean> transfer(long accountId1, long accountId2, int amount) {
        return null;
    }
}
