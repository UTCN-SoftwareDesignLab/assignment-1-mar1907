package service.employee;

import model.Employee;
import model.validation.Notification;
import repository.employee.AuthenticationException;
import repository.employee.EmployeeRepository;
import repository.security.RightsRolesRepository;

import java.security.MessageDigest;

public class AuthenticationServiceMySQL implements AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public AuthenticationServiceMySQL(EmployeeRepository employeeRepository, RightsRolesRepository rightsRolesRepository) {
        this.employeeRepository = employeeRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }


    //TODO rethink validators?
    @Override
    public Notification<Boolean> register(String username, String password) {
        return null;
    }

    @Override
    public Notification<Employee> login(String username, String password) throws AuthenticationException {
        return null;
    }


    //TODO eliminate?
    @Override
    public boolean logout(Employee user) {
        return false;
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
