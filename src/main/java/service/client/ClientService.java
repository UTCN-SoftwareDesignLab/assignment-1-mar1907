package service.client;

import model.Client;
import model.validation.Notification;
import service.Service;

import java.util.List;

public interface ClientService extends Service{

    List<Client> getClients();

    Notification<Client> getClient(Long clientId);

    Notification<Boolean> insertClient(String name, String idCard, String cnp);

    Notification<Boolean> updateClient(Long clientId, String name, String idCard, String cnp);

    Notification<Boolean> deleteClient(Long clientId);
}
