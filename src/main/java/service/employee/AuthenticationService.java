package service.employee;

import model.Employee;
import model.validation.Notification;
import repository.employee.AuthenticationException;
import service.Service;

public interface AuthenticationService extends Service{

    Notification<Boolean> register(String username, String password);

    Notification<Employee> login(String username, String password) throws AuthenticationException;

    String encodePassword(String password);

    boolean logout(Employee user);
}
