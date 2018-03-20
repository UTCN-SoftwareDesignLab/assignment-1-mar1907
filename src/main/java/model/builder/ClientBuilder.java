package model.builder;

import model.Account;
import model.Bill;
import model.Client;

import java.util.List;

public class ClientBuilder {

    private Client client;

    public ClientBuilder(Client client) {
        this.client = client;
    }

    public ClientBuilder setId(Long id){
        client.setId(id);
        return this;
    }

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setIdCard(String idCard){
        client.setIdCard(idCard);
        return this;
    }

    public ClientBuilder setCNP(String CNP){
        client.setCNP(CNP);
        return this;
    }

    public ClientBuilder setAccountList(List<Account> accountList){
        client.setAccountList(accountList);
        return this;
    }

    public ClientBuilder setBillsList(List<Bill> billsList){
        client.setBillList(billsList);
        return this;
    }

    public Client build(){
        return client;
    }
}
