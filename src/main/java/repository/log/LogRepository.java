package repository.log;

import model.Log;

import java.util.Date;
import java.util.List;

public interface LogRepository {

    boolean save(Log log, long employeeId);

    List<Log> getLogs(Date from, Date to, long employeeId);
}
