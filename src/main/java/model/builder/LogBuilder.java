package model.builder;

import model.Log;

import java.util.Date;

public class LogBuilder {

    private Log log;

    public LogBuilder() {
        log = new Log();
    }

    public LogBuilder setId(long id){
        log.setId(id);
        return this;
    }

    public LogBuilder setText(String text){
        log.setText(text);
        return this;
    }

    public LogBuilder setDate(Date date){
        log.setDate(date);
        return this;
    }

    public Log build(){
        return log;
    }
}
