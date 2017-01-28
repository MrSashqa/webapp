package project.controller.validator;

import java.util.Map;

public interface Validator<T> {

    boolean validate(T t);

    boolean validate(T t, Map<String, String> errors);
}
