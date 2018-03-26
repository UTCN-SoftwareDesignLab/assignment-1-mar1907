package model.builder;

import model.SavingsAccount;

public class SavingsAccountBuilder extends AccountBuilder {

    public SavingsAccountBuilder() {
        account = new SavingsAccount();
    }

    public SavingsAccountBuilder setInterest(double interest){
        ((SavingsAccount)account).setInterest(interest);
        return this;
    }
}
