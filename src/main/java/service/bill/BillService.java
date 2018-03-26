package service.bill;

import model.validation.Notification;
import service.Service;

public interface BillService extends Service {

    Notification<Boolean> payBill(long accountId, int amount, String text);
}
