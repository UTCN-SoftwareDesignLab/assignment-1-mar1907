package repository.account;

import model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account getAccount(long id);

    boolean saveAccount(Account account, Long clientID, Long typeID);

    boolean updateAccount(Account account, Long clientId, Long typeID);

    boolean delete(Account account);

    long getClientId(long accountId);

    long getTypeId(String type);

    long getAccountType(long accountId);

    void addType(String type);
}
