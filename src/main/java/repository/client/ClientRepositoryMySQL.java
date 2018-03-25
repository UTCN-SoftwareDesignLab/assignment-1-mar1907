package repository.client;

import model.Client;
import model.builder.ClientBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + CLIENT);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Client client = new ClientBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setCNP(resultSet.getString("cnp"))
                        .setIdCard(resultSet.getString("idcard"))
                        //add other stuff?
                        .build();
                clientList.add(client);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return clientList;
    }

    @Override
    public boolean addClient(Client client) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT  INTO " + CLIENT + " VALUES (null, ?, ?, ?)"
            );
            statement.setString(1, client.getName());
            statement.setString(2, client.getIdCard());
            statement.setString(3, client.getCNP());

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {

        try {
            PreparedStatement statement = connection.prepareStatement(
              "UPDATE " + CLIENT + " SET name=?, idcard=?, cnp=? WHERE id=?"
            );
            statement.setString(1,client.getName());
            statement.setString(2,client.getIdCard());
            statement.setString(3,client.getCNP());
            statement.setLong(4,client.getId());

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
