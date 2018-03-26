package repository.bill;

import model.Bill;

public interface BillRepository {

    boolean addBill(Bill bill, long accountId);
}
