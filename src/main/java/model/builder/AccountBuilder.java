package model.builder;

import model.Account;

public class AccountBuilder {

    protected Account account;

    public AccountBuilder(Account account) {
        this.account = account;
    }

    public AccountBuilder setID(Long id){
        account.setId(id);
        return this;
    }

    public AccountBuilder setAmount(int amount){
        account.setAmount(amount);
        return this;
    }

    public Account build(){
        return account;
    }
}
