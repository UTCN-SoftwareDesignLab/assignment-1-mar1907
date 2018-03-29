package service.log;

import factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LogServiceMySQLTest {

    private LogService logService;

    @Before
    public void setUp() throws Exception {
        logService = ServiceFactory.instance().getLogService();
        logService.setEmpId(1);
        logService.saveLog("text");
    }

    @Test
    public void saveLog() {
        long count = logService.getLogs(logService.getEmpId(),nowToString(),nowToString()).getResult().chars().filter(num -> num == '\n').count();
        logService.saveLog("text");
        long newCount = logService.getLogs(logService.getEmpId(),nowToString(),nowToString()).getResult().chars().filter(num -> num == '\n').count();
        assertEquals(count+1,newCount);
    }

    @Test
    public void getLogs() {
        String text = logService.getLogs(logService.getEmpId(),nowToString(),nowToString()).getResult();
        text = text.split("\n")[1].split(":")[1];
        text = text.trim();
        assertEquals("text",text);
    }

    private String nowToString(){
        Date date = new Date();
        String month = (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1)+"";
        String day = date.getDate()<10?"0"+date.getDate():date.getDate()+"";
        String result = (date.getYear()+1900) + "-" + month + "-" + day;
        return result;
    }
}