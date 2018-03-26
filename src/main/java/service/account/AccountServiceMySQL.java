package service.account;

import model.Account;
import model.builder.AccountBuilder;
import model.builder.SavingsAccountBuilder;
import model.validation.AccountValidator;
import model.validation.Notification;
import repository.account.AccountRepository;

import java.util.List;

import static database.Constants.Types.SAVING;
import static database.Constants.Types.SPENDING;

public class AccountServiceMySQL implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String getAccountData() {
        List<Account> accountList = accountRepository.findAll();
        String data = "";
        for (Account account : accountList) {
            data += account.toString() + " " + accountRepository.getClientId(account.getId()) + "\n";
        }

        return data;
    }

    @Override
    public Notification<Boolean> insertAccount(int amount, long clientID, boolean saving, double interest) {
        Account acc = formAccount(amount, saving, interest);
        Long type = saving ? accountRepository.getTypeId(SAVING) : accountRepository.getTypeId(SPENDING);

        AccountValidator accountValidator = new AccountValidator(acc);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            notification.setResult(accountRepository.saveAccount(acc, clientID, type));
            return notification;
        }
    }

    private Account formAccount(int amount, boolean saving, double interest) {
        return saving ? new SavingsAccountBuilder()
                .setInterest(interest)
                .setAmount(amount)
                .build() : new AccountBuilder()
                .setAmount(amount)
                .build();
    }

    private Account formAccount(long id, int amount, boolean saving, double interest) {
        return saving ? new SavingsAccountBuilder()
                .setInterest(interest)
                .setID(id)
                .setAmount(amount)
                .build() : new AccountBuilder()
                .setID(id)
                .setAmount(amount)
                .build();
    }

    @Override
    public Notification<Boolean> updateAccount(long id, int amount, long clientID, boolean saving, double interest) {
        Account acc = formAccount(id, amount, saving, interest);
        Long type = saving ? accountRepository.getTypeId(SAVING) : accountRepository.getTypeId(SPENDING);

        AccountValidator accountValidator = new AccountValidator(acc);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            notification.setResult(accountRepository.updateAccount(acc, clientID, type));
            return notification;
        }

    }

    @Override
    public Notification<Account> getAccount(long id) {
        Notification<Account> notification = new Notification<>();
        Account account = accountRepository.getAccount(id);
        notification.setResult(account);

        if (account == null) {
            notification.addError("Could not find account");
        }

        return notification;
    }

    @Override
    public Notification<Boolean> delete(long id) {
        Account account = new AccountBuilder()
                .setID(id)
                .build();

        Notification<Boolean> notification = new Notification<>();
        notification.setResult(accountRepository.delete(account));
        return notification;
    }
}
