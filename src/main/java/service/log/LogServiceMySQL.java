package service.log;

import model.Log;
import model.builder.LogBuilder;
import model.validation.DateValidator;
import model.validation.Notification;
import repository.log.LogRepository;

import java.util.Date;
import java.util.List;

public class LogServiceMySQL implements LogService {

    private LogRepository logRepository;
    private long empId;

    public LogServiceMySQL(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void setEmpId(long empId) {
        this.empId = empId;
    }

    @Override
    public long getEmpId() {
        return empId;
    }

    @Override
    public void saveLog(String text) {
        Log log = new LogBuilder()
                .setText(text)
                .setDate(new Date())
                .build();
        logRepository.save(log,empId);
    }

    @Override
    public Notification<String> getLogs(long id, String from, String to) {
        DateValidator dv1 = new DateValidator(from);
        DateValidator dv2 = new DateValidator(to);
        Notification<String> notification = new Notification<>();
        if(dv1.validate() && dv2.validate()) {
            List<Log> logList = logRepository.getLogs(convertStringToDate(from), convertStringToDate(to), id);
            String data = "Data from employee " + id + " from " + from + " to " + to + ":\n";
            for (Log l : logList) {
                data += l.getDate() + ": " + l.getText() + "\n";
            }
            notification.setResult(data);
        }
        else{
            dv1.getErrors().forEach(notification::addError);
            dv2.getErrors().forEach(notification::addError);
            notification.setResult("");
        }

        return notification;
    }

    private Date convertStringToDate(String string){
        Date date = new Date();
        String[] strings = string.split("-");
        date.setYear(Integer.parseInt(strings[0]));
        date.setMonth(Integer.parseInt(strings[1]));
        date.setDate(Integer.parseInt(strings[2]));
        return date;
    }
}
