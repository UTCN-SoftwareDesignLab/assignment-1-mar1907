package service.transfer;

import model.Account;
import model.validation.Notification;
import repository.account.AccountRepository;
import service.account.AccountService;

public class TransferServiceMySQL implements TransferService {

    private AccountRepository accountRepository;
    private AccountService accountService;

    public TransferServiceMySQL(AccountRepository accountRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @Override
    public Notification<Boolean> transfer(long accountId1, long accountId2, int amount) {
        Notification<Boolean> notification = new Notification<>();

        Notification<Account> notificationAcc1 = accountService.getAccount(accountId1);
        Notification<Account> notificationAcc2 = accountService.getAccount(accountId2);

        if(notificationAcc1.hasErrors()){
            notification.addError("Could not find account 1");
        }

        if(notificationAcc2.hasErrors()){
            notification.addError("Could not find account 2");
        }

        if(notification.hasErrors()){
            notification.setResult(Boolean.FALSE);
            return notification;
        }

        Account account1 = notificationAcc1.getResult();
        Account account2 = notificationAcc2.getResult();

        if(account1.getAmount()<amount){
            notification.addError("Not enough money");
            notification.setResult(Boolean.FALSE);
            return notification;
        }

        notification.setResult(Boolean.TRUE);
        boolean res;
        account1.setAmount(account1.getAmount()-amount);
        res = accountRepository.updateAccount(account1, accountRepository.getClientId(account1.getId()), accountRepository.getAccountType(account1.getId()));
        notification.setResult(res && notification.getResult());
        account2.setAmount(account2.getAmount()+amount);
        res = accountRepository.updateAccount(account2, accountRepository.getClientId(account2.getId()), accountRepository.getAccountType(account2.getId()));
        notification.setResult(res && notification.getResult());

        if(!notification.getResult()){
            notification.addError("Could not update accounts");
        }

        return notification;
    }
}
