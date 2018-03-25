package model.validation;

import model.Account;
import model.SavingsAccount;

public class SavingsAccountValidator extends AccountValidator {

    public SavingsAccountValidator(SavingsAccount account) {
        super(account);
    }

    @Override
    public boolean validate() {
        return super.validate();
    }

    private void validateInterest(double interest){
        if(interest<0){
            errors.add("Invalid interest");
        }
    }
}
