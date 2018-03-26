package repository.account;

import model.Account;
import model.Client;
import model.SavingsAccount;
import model.builder.AccountBuilder;
import model.builder.SavingsAccountBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;
import static database.Constants.Tables.TYPE;
import static database.Constants.Types.SAVING;

public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + ACCOUNT
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long accountTypeId = resultSet.getLong("type_id");
                if(isSavingsAccount(accountTypeId)){
                    SavingsAccount account = (SavingsAccount) new SavingsAccountBuilder()
                            .setInterest(resultSet.getInt("interest"))
                            .setID(resultSet.getLong("id"))
                            .setAmount(resultSet.getInt("amount"))
                            .build();
                    accountList.add(account);
                } else {
                    Account account = new AccountBuilder()
                            .setID(resultSet.getLong("id"))
                            .setAmount(resultSet.getInt("amount"))
                            .build();
                    accountList.add(account);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return accountList;
    }

    @Override
    public void addType(String type) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + TYPE + " VALUES (null, ?)"
            );
            statement.setString(1,type);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveAccount(Account account, Long clientID, Long typeId) {
        try {
            PreparedStatement statement;

            if(isSavingsAccount(typeId)) {

                statement = connection.prepareStatement(
                        "INSERT  INTO " + ACCOUNT + " VALUES (null, ?, ?, ?, ?)"
                );
                statement.setInt(1, account.getAmount());
                statement.setFloat(2,(float)((SavingsAccount)account).getInterest());
                statement.setLong(3, clientID);
                statement.setLong(4,typeId);
            } else {
                statement = connection.prepareStatement(
                        "INSERT INTO " + ACCOUNT + " VALUES (null, ?, null, ?, ?)"
                );
                statement.setInt(1, account.getAmount());
                statement.setLong(2, clientID);
                statement.setLong(3,typeId);
            }

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account, Long clientID, Long typeId) {
        try {
            PreparedStatement statement;

            if(isSavingsAccount(typeId)) {

                statement = connection.prepareStatement(
                        "UPDATE " + ACCOUNT + " SET amount=?, interest=?, client_id=?, type_id=? WHERE id=?"
                );
                statement.setInt(1, account.getAmount());
                statement.setFloat(2,(float)((SavingsAccount)account).getInterest());
                statement.setLong(3, clientID);
                statement.setLong(4,typeId);
                statement.setLong(4,account.getId());
            } else {
                statement = connection.prepareStatement(
                        "UPDATE " + ACCOUNT + " SET amount=?, interest=null, client_id=?, type_id=? WHERE id=?"
                );
                statement.setInt(1, account.getAmount());
                statement.setLong(2, clientID);
                statement.setLong(3,typeId);
                statement.setLong(4,account.getId());
            }

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Account account) {
        try{
            PreparedStatement statement = connection.prepareStatement(
              "DELETE FROM " + ACCOUNT + " WHERE id=?"
            );
            statement.setLong(1,account.getId());

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account getAccount(long id) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + ACCOUNT + " WHERE id=?"
            );
            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            Account account = new AccountBuilder()
                    .setID(id)
                    .setAmount(resultSet.getInt("amount"))
                    .build();

            return account;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getTypeId(String type){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM " + TYPE + " WHERE type=?"
            );
            statement.setString(1,type);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getLong("id");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public long getClientId(long accountId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT client_id FROM " + ACCOUNT + " WHERE id=?"
            );
            statement.setLong(1,accountId);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getLong("client_id");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public long getAccountType(long accountId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT type_id FROM " + ACCOUNT + " WHERE id=?"
            );
            statement.setLong(1,accountId);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getLong("type_id");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    private boolean isSavingsAccount(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT type FROM " + TYPE + " WHERE id=?"
            );
            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("type").equals(SAVING)){
                    return true;
                }
            }

            return false;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
