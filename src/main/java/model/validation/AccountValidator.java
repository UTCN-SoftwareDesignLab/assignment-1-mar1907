package model.validation;

import model.Account;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class AccountValidator {

    private final Account account;
    protected final List<String> errors;

    public AccountValidator(Account account) {
        this.account = account;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateAmount(account.getAmount());
        return errors.isEmpty();
    }

    private void validateAmount(int amount){
        if(amount<0){
            errors.add("Invalid amount");
        }
    }
}
