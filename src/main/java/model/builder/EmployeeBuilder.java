package model.builder;

import model.Employee;
import model.Role;

import java.util.List;

public class EmployeeBuilder {

    private Employee employee;

    public EmployeeBuilder() {
        employee = new Employee();
    }

    public EmployeeBuilder setID(Long id){
        employee.setId(id);
        return this;
    }

    public EmployeeBuilder setUsername(String username){
        employee.setUsername(username);
        return this;
    }

    public EmployeeBuilder setPassword(String password){
        employee.setPassword(password);
        return this;
    }

    public EmployeeBuilder setRolesList(List<Role> rolesList){
        employee.setRoles(rolesList);
        return this;
    }

    public Employee build(){
        return employee;
    }
}
