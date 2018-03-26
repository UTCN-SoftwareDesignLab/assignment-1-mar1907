package service.transfer;

import model.validation.Notification;
import service.Service;

public interface TransferService extends Service{

    Notification<Boolean> transfer(long accountId1, long accountId2, int amount);
}
