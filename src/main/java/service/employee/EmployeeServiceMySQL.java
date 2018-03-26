package service.employee;

import model.Employee;
import model.Role;
import model.builder.EmployeeBuilder;
import model.validation.EmployeeValidator;
import model.validation.Notification;
import repository.employee.AuthenticationException;
import repository.employee.EmployeeRepository;
import repository.security.RightsRolesRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceMySQL implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private RightsRolesRepository rightsRolesRepository;
    private AuthenticationService authenticationService;

    public EmployeeServiceMySQL(EmployeeRepository employeeRepository, RightsRolesRepository rightsRolesRepository, AuthenticationService authenticationService) {
        this.employeeRepository = employeeRepository;
        this.rightsRolesRepository = rightsRolesRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public String getEmployeeData() {
        List<Employee> employeeList = employeeRepository.findAll();
        String text = "";
        for(Employee e: employeeList){
            text += e + " ";
            List<Role> roleList = rightsRolesRepository.findRolesForEmployee(e.getId());
            for(Role r: roleList){
                text += r.getRole() + " ";
            }
            text += "\n";
        }

        return text;
    }

    @Override
    public Notification<Boolean> addEmployee(String username, String password, boolean admin) {
        Notification<Boolean> notification = new Notification<>();

        Notification<Boolean> notification1 = authenticationService.register(username,password);
        if(notification1.hasErrors()) {
            notification.addError(notification1.getFormattedErrors());
            notification.setResult(Boolean.FALSE);
            return notification;
        }

        try {
            Notification<Employee> notification2 = authenticationService.login(username, password);
            if(notification2.hasErrors()){
                notification.addError(notification2.getFormattedErrors());
                notification.setResult(Boolean.FALSE);
                return notification;
            }

            Employee emp = notification2.getResult();
            if(admin){
                List<Role> roleList = new ArrayList<>();
                roleList.add(rightsRolesRepository.findRoleByTitle("administrator"));
                rightsRolesRepository.addRolesToEmployee(emp,roleList);
                notification.setResult(Boolean.TRUE);
            } else{
                notification.setResult(Boolean.TRUE);
            }
        } catch (AuthenticationException ex){
            ex.printStackTrace();
        }

        return notification;
    }

    @Override
    public Notification<Boolean> updateEmployee(long id, String username, String password, boolean admin) {
        Employee employee = new EmployeeBuilder()
                .setUsername(username)
                .setID(id)
                .setPassword(password)
                .build();

        if(admin){
            List<Role> roleList = new ArrayList<>();
            roleList.add(rightsRolesRepository.findRoleByTitle("administrator"));
            employee.setRoles(roleList);
        } else {
            List<Role> roleList = new ArrayList<>();
            roleList.add(rightsRolesRepository.findRoleByTitle("employee"));
            employee.setRoles(roleList);
        }

        EmployeeValidator employeeValidator = new EmployeeValidator(employee);
        boolean isValid = employeeValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!isValid){
            employeeValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            employee.setPassword(authenticationService.encodePassword(password));
            notification.setResult(employeeRepository.update(employee));
            return notification;
        }
    }

    @Override
    public Notification<Boolean> delete(long id) {
        Employee employee = new EmployeeBuilder()
                .setID(id)
                .build();

        Notification<Boolean> notification = new Notification<>();
        notification.setResult(employeeRepository.delete(employee));
        return notification;
    }
}
