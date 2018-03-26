package repository.log;

import model.Log;
import model.builder.LogBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.Constants.Tables.LOG;

public class LogRepositoryMySQL implements LogRepository {

    private Connection connection;

    public LogRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Log log, long employeeId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT  INTO " + LOG + " VALUES (null, ?, ?, ?)"
            );
            statement.setLong(1, employeeId);
            statement.setString(2, log.getText());
            statement.setDate(3, new java.sql.Date(log.getDate().getTime()));

            statement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private String dateToString(Date date) {
        String month = date.getMonth()+"";
        month = month.length()==1?"0"+month:month;
        String day = date.getDate()+"";
        day = day.length()==1?"0"+day:day;
        return date.getYear()+"-"+month+"-"+day;
    }

    @Override
    public List<Log> getLogs(Date from, Date to, long employeeID) {
        try {
            List<Log> logList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + LOG + " WHERE employee_id=? AND moment between ? and ?"
            );
            statement.setLong(1,employeeID);
            statement.setString(2, dateToString(from));
            statement.setString(3, dateToString(to));

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Log log = new LogBuilder()
                        .setDate(resultSet.getDate("moment"))
                        .setId(resultSet.getLong("id"))
                        .setText(resultSet.getString("text"))
                        .build();

                logList.add(log);
            }

            return logList;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
