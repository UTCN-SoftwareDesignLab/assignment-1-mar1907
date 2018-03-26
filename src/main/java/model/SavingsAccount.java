package model;

public class SavingsAccount extends Account {
    private double interest;

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return getId() + " " + getAmount() + " " + interest;
    }
}
