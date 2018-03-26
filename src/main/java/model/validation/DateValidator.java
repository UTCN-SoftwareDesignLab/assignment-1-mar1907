package model.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DateValidator {

    private static final String DATE_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    private final String date;
    private final List<String> errors;

    public DateValidator(String date) {
        this.date = date;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateDate(date);

        return errors.isEmpty();
    }

    private void validateDate(String date){
        if (!Pattern.compile(DATE_REGEX).matcher(date).matches()) {
            errors.add("Invalid date format!");
        }
    }
}
