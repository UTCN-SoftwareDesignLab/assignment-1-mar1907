package service.log;

import model.validation.Notification;
import service.Service;

import java.util.Date;

public interface LogService extends Service {

    void setEmpId(long empId);

    long getEmpId();

    void saveLog(String text);

    Notification<String> getLogs(long id, String from, String to);
}
