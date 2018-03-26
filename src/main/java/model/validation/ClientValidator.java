package model.validation;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator {

    private static final String NAME_REGEX = "[a-zA-Z ]+";
    private static final String CNP_REGEX = "[0-9]{13}";
    private static final String ID_REGEX = "[A-Z]{2}[0-9]{6}";


    private final Client client;
    private final List<String> errors;

    public ClientValidator(Client client) {
        this.client = client;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateName(client.getName());
        validateIDCard(client.getIdCard());
        validateCNP(client.getCNP());

        return errors.isEmpty();
    }

    private void validateName(String name){
        if (!Pattern.compile(NAME_REGEX).matcher(name).matches()) {
            errors.add("Invalid name!");
        }
    }

    private void validateCNP(String cnp){
        if (!Pattern.compile(CNP_REGEX).matcher(cnp).matches()) {
            errors.add("Invalid cnp!");
        }
    }

    private void validateIDCard(String id){
        if (!Pattern.compile(ID_REGEX).matcher(id).matches()) {
            errors.add("Invalid idcard!");
        }
    }
}
