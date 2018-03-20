package repository.employee;

import model.Employee;
import model.builder.EmployeeBuilder;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables;
import static database.Constants.Tables.EMPLOYEE;

public class EmployeeRepositoryMySQL implements EmployeeRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;

    public EmployeeRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + EMPLOYEE);
            ResultSet employeesResultSet = statement.executeQuery();
            while(employeesResultSet.next()){
                Employee employee = new EmployeeBuilder()
                        .setID(employeesResultSet.getLong("id"))
                        .setUsername(employeesResultSet.getString("username"))
                        .setPassword(employeesResultSet.getString("password"))
                        .setRolesList(rightsRolesRepository.findRolesForEmployee(employeesResultSet.getLong("id")))
                        .build();
                employeeList.add(employee);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public Notification<Employee> findByNameAndPassword(String name, String password) throws AuthenticationException {
        Notification<Employee> findByNameAndPasswordNotification = new Notification<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "Select * from `" + EMPLOYEE + "` where `username`=? and `password`=?");
            ResultSet employeeResultSet = statement.executeQuery();
            if(employeeResultSet.next()){
                Employee user = new EmployeeBuilder()
                        .setUsername(employeeResultSet.getString("username"))
                        .setPassword(employeeResultSet.getString("password"))
                        .setRolesList(rightsRolesRepository.findRolesForEmployee(employeeResultSet.getLong("id")))
                        .build();
                findByNameAndPasswordNotification.setResult(user);
                return findByNameAndPasswordNotification;
            } else{
                findByNameAndPasswordNotification.addError("Invalid name or password!");
                return findByNameAndPasswordNotification;
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public boolean save(Employee employee) {
        try {
            PreparedStatement insertEmployeeStatement = connection
                    .prepareStatement("INSERT INTO" + EMPLOYEE + "values (null, ?, ?)");
            insertEmployeeStatement.setString(1, employee.getUsername());
            insertEmployeeStatement.setString(2, employee.getPassword());
            insertEmployeeStatement.executeUpdate();

            ResultSet rs = insertEmployeeStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            employee.setId(userId);

            rightsRolesRepository.addRolesToEmployee(employee, employee.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + EMPLOYEE + "WHERE id>=0");
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
