package service.employee;

import model.validation.Notification;
import service.Service;

public interface EmployeeService extends Service {

    String getEmployeeData();

    Notification<Boolean> addEmployee(String username, String password, boolean admin);

    Notification<Boolean> updateEmployee(long id, String username, String password, boolean admin);

    Notification<Boolean> delete(long id);
}
