package repository.bill;

import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static database.Constants.Tables.BILL;

public class BillRepositoryMySQL implements BillRepository {

    private Connection connection;

    public BillRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addBill(Bill bill, long accountId) {
        try{
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO " + BILL + " VALUES (null,?,?,?)"
            );
            statement.setLong(1,bill.getAmount());
            statement.setString(2,bill.getText());
            statement.setLong(3,accountId);

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
