package model.validation;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeValidator {
    //TODO

    private final Employee employee;
    private final List<String> errors;

    public EmployeeValidator(Employee employee) {
        this.employee = employee;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        return true;
    }
}
