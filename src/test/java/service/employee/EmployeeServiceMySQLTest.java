package service.employee;

import factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeServiceMySQLTest {

    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        employeeService = ServiceFactory.instance().getEmployeeService();
    }

    @Test
    public void getEmployeeData() {
        assertEquals("1", employeeService.getEmployeeData().split("\n")[0].split(" ")[0]);
    }

    @Test
    public void addEmployee() {
        long count = employeeService.getEmployeeData().chars().filter(num -> num == '\n').count();
        employeeService.addEmployee("Namename", "parola1!", false);
        long newCount = employeeService.getEmployeeData().chars().filter(num -> num == '\n').count();
        assertEquals(count+1,newCount);
    }

    @Test
    public void updateEmployee() {
        employeeService.updateEmployee(1,"nname","parola1!",false);
        String name = employeeService.getEmployeeData().split("\n")[0].split(" ")[1];
        assertEquals(name,"nname");
    }

    @Test
    public void delete() {
        employeeService.addEmployee("Name", "parola1!", false);
        long count = employeeService.getEmployeeData().chars().filter(num -> num == '\n').count();
        employeeService.delete(3L);
        long newCount = employeeService.getEmployeeData().chars().filter(num -> num == '\n').count();
        assertEquals(count-1,newCount);
    }
}