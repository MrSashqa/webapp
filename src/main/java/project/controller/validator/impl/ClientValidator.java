package project.controller.validator.impl;

import project.controller.validator.Validator;
import project.model.entity.Client;
import project.util.JspMessage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator implements Validator<Client> {

    @Override
    public boolean validate(Client client) {
        return validateFirstName(client.getFirstName()) &&
                validateLastName(client.getLastName()) &&
                validatePhoneNumber(client.getPhoneNumber()) &&
                validateEmail(client.getPhoneNumber());
    }

    @Override
    public boolean validate(Client client, Map<String, String> errors) {
        boolean result = true;
        if (!validateFirstName(client.getFirstName())) {
            errors.put("firstName", JspMessage.WRONG_FIRST_NAME);
            result = false;
        }
        if (!validateLastName(client.getLastName())) {
            errors.put("lastName", JspMessage.WRONG_LAST_NAME);
            result = false;
        }
        if (!validatePhoneNumber(client.getPhoneNumber())) {
            errors.put("phoneNumber", JspMessage.WRONG_PHONE_NUMBER);
            result = false;
        }
        if (!validateEmail(client.getEmail())) {
            errors.put("email", JspMessage.WRONG_EMAIL);
            result = false;
        }
        return result;
    }

    private boolean validateFirstName(String firstName) {
        String name = firstName.replaceAll("[ ]{2,}", " ");
        Pattern pattern = Pattern.compile("([-A-Za-z ']+)|([-А-Яа-я ']+)");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches() && name.length() <= 32;
    }

    private boolean validateLastName(String lastName) {
        return validateFirstName(lastName);
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("([-a-zA-Z0-9_]+)@([a-z]{4,})[.]([a-z]{2,3})");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches()&& email.length() <= 32;
    }

    private boolean validatePhoneNumber(String phone) {
        String number = phone.replaceAll("[- */()_]", "");
        Pattern pattern = Pattern.compile("(^[+]?)([0-9]{0,3})([0-9]{10})");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }


}
