package service.bill;

import model.Account;
import model.Bill;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.bill.BillRepository;

public class BillServiceMySQL implements BillService {

    private AccountRepository accountRepository;
    private BillRepository billRepository;

    public BillServiceMySQL(AccountRepository accountRepository, BillRepository billRepository) {
        this.accountRepository = accountRepository;
        this.billRepository = billRepository;
    }

    @Override
    public Notification<Boolean> payBill(long accountId, int amount, String text) {
        Notification<Boolean> notification = new Notification<>();

        Account account = accountRepository.getAccount(accountId);
        if(account==null){
            notification.addError("No account found!");
            notification.setResult(Boolean.FALSE);
            return notification;
        }

        if(account.getAmount()<amount){
            notification.addError("Not enough money!");
            notification.setResult(Boolean.FALSE);
            return notification;
        }

        account.setAmount(account.getAmount()-amount);
        boolean res = accountRepository.updateAccount(account,accountRepository.getClientId(accountId),accountRepository.getAccountType(accountId));
        if(!res){
            notification.setResult(Boolean.FALSE);
            notification.addError("Problem updating account");
            return notification;
        }

        Bill bill = new Bill();
        bill.setAmount(amount);
        bill.setText(text);
        billRepository.addBill(bill, accountId);

        notification.setResult(Boolean.TRUE);
        return notification;
    }
}
