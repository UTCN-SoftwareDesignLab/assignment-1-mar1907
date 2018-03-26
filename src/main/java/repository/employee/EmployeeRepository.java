package repository.employee;

import model.Employee;
import model.validation.Notification;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();

    Notification<Employee> findByNameAndPassword(String name, String password) throws AuthenticationException;

    boolean save(Employee employee);

    boolean update(Employee employee);

    boolean delete(Employee employee);
}
