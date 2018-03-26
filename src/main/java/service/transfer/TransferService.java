package service.transfer;

import model.validation.Notification;

public interface TransferService {

    Notification<Boolean> transfer(long accountId1, long accountId2, int amount);
}
