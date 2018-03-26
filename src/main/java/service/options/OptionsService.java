package service.options;

import model.Employee;
import service.Service;

import java.util.List;

public interface OptionsService extends Service {

    List<Boolean> getOptions(Employee employee);
}
