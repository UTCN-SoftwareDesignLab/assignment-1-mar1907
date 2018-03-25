package repository.account;

import model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    void addType(String type);
}
