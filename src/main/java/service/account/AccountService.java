package service.account;

import model.Account;
import model.validation.Notification;
import service.Service;

public interface AccountService extends Service{

    String getAccountData();

    Notification<Account> getAccount(long id);

    Notification<Boolean> insertAccount(int amount, long clientID, boolean saving, double interest);

    Notification<Boolean> updateAccount(long id, int amount, long clientID, boolean saving, double interest);

    Notification<Boolean> delete(long id);

}
