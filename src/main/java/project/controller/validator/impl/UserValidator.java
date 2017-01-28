package project.controller.validator.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.util.JspMessage;
import project.controller.validator.Validator;
import project.model.entity.User;

public class UserValidator implements Validator<User> {

    @Override
    public boolean validate(User user) {
        return validateLogin(user.getLogin()) && validatePassword(user.getPassword());
    }

    @Override
    public boolean validate(User user, Map<String, String> errors) {
        boolean result = true;
        if (!validateLogin(user.getLogin())) {
            errors.put("login", JspMessage.WRONG_LOGIN_FORMAT);
            result = false;
        }
        if (!validatePassword(user.getPassword())) {
            errors.put("password", JspMessage.WRONG_PASSWORD_FORMAT);
            result = false;
        }
        return result;
    }

    private boolean validatePassword(String password) {
         password = password.replaceAll("[ ]", "");
         if (password.length() < 8) {
            return false;
        }
        return true;
    }

    private boolean validateLogin(String login) {
        Pattern pattern = Pattern.compile("[a-zA-z0-9]{4,32}");
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

}
