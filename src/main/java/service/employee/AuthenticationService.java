package service.employee;

import model.Employee;
import model.validation.Notification;
import repository.employee.AuthenticationException;

public interface AuthenticationService {

    Notification<Boolean> register(String username, String password);

    Notification<Employee> login(String username, String password) throws AuthenticationException;

    boolean logout(Employee user);
}
