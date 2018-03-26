package repository.account;

import model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    boolean saveAccount(Account account, Long clientID, Long typeID);

    boolean updateAccount(Account account, Long clientId, Long typeID);

    boolean delete(Account account);

    long getClientId(long accountId);

    long getTypeId(String type);

    void addType(String type);
}
