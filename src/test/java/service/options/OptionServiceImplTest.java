package service.options;

import factory.ServiceFactory;
import model.Employee;
import model.Role;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OptionServiceImplTest {

    private OptionsService optionsService;

    @Before
    public void setUp() throws Exception {
        optionsService = ServiceFactory.instance().getOptionsService();
    }

    @Test
    public void getOptions() {
        Employee employee = new Employee();
        employee.setRoles(Arrays.asList(new Role(1L,"",null)));
        List<Boolean> r = optionsService.getOptions(employee);
        List<Boolean> res = new ArrayList<>();
        res.add(Boolean.FALSE);
        res.add(Boolean.FALSE);
        res.add(Boolean.FALSE);
        res.add(Boolean.FALSE);
        res.add(Boolean.TRUE);
        res.add(Boolean.TRUE);
        assertEquals(res,r);
    }
}