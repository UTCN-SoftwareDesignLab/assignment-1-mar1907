package service.employee;

import model.Employee;
import model.Role;
import model.builder.EmployeeBuilder;
import model.validation.EmployeeValidator;
import model.validation.Notification;
import repository.employee.AuthenticationException;
import repository.employee.EmployeeRepository;
import repository.security.RightsRolesRepository;

import java.security.MessageDigest;
import java.util.Collections;

import static database.Constants.Roles.EMPLOYEE;

public class AuthenticationServiceMySQL implements AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public AuthenticationServiceMySQL(EmployeeRepository employeeRepository, RightsRolesRepository rightsRolesRepository) {
        this.employeeRepository = employeeRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }


    @Override
    public Notification<Boolean> register(String username, String password) {
        Role employeeRole = rightsRolesRepository.findRoleByTitle(EMPLOYEE);
        Employee employee =  new EmployeeBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRolesList(Collections.singletonList(employeeRole))
                .build();

        EmployeeValidator employeeValidator = new EmployeeValidator(employee);
        boolean employeeValid = employeeValidator.validate();
        Notification<Boolean> employeeRegisterNotification = new Notification<>();

        if(!employeeValid){
            employeeValidator.getErrors().forEach(employeeRegisterNotification::addError);
            employeeRegisterNotification.setResult(Boolean.FALSE);
            return employeeRegisterNotification;
        } else {
            employee.setPassword(encodePassword(password));
            employeeRegisterNotification.setResult(employeeRepository.save(employee));
            return employeeRegisterNotification;
        }
    }

    @Override
    public Notification<Employee> login(String username, String password) throws AuthenticationException {
        return employeeRepository.findByNameAndPassword(username, encodePassword(password));
    }



    public String encodePassword(String password) {
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
