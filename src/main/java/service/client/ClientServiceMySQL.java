package service.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.client.ClientRepository;

import java.util.List;

public class ClientServiceMySQL implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String getClientsData() {
        List<Client> clientList = clientRepository.findAll();
        String data = "";
        for(Client c: clientList){
            data += c.getId() + " " + c.getName() + " " + c.getIdCard() + " " + c.getCNP() + "\n";
        }

        return data;
    }

    @Override
    public Notification<Boolean> insertClient(String name, String idCard, String cnp) {
        Client client = new ClientBuilder()
                .setName(name)
                .setIdCard(idCard)
                .setCNP(cnp)
                .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!clientValid){
            clientValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            notification.setResult(clientRepository.addClient(client));
            return notification;
        }

    }

    @Override
    public Notification<Boolean> updateClient(Long clientId, String name, String idCard, String cnp) {
        Client client = new ClientBuilder()
                .setId(clientId)
                .setName(name)
                .setIdCard(idCard)
                .setCNP(cnp)
                .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!clientValid){
            clientValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            notification.setResult(clientRepository.update(client));
            return notification;
        }
    }
}
