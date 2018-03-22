package service.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public class ClientServiceMySQL implements ClientService {

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public Notification<Client> getClient(Long clientId) {
        return null;
    }

    @Override
    public Notification<Boolean> insertClient(String name, String idCard, String cnp) {
        return null;
    }

    @Override
    public Notification<Boolean> updateClient(Long clientId, String name, String idCard, String cnp) {
        return null;
    }

    @Override
    public Notification<Boolean> deleteClient(Long clientId) {
        return null;
    }
}
