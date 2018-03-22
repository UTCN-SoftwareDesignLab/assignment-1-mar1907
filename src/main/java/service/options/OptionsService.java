package service.options;

import model.Employee;

import java.util.List;

public interface OptionsService {

    public List<Boolean> getOptions(Employee employee);
}
