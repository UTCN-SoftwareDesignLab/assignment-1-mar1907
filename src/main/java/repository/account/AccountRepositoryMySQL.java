package repository.account;

import model.Account;
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

    private boolean isSavingsAccount(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT type FROM " + TYPE + " WHERE id=?"
            );
            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("name").equals(SAVING)){
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
