package project.controller.validator.impl;

import project.controller.validator.Validator;
import project.model.entity.Dish;
import project.util.JspMessage;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DishValidator implements Validator<Dish> {
    @Override
    public boolean validate(Dish dish) {
        return validateType(dish.getType()) &&
                validateName(dish.getName()) &&
                validatePrice(dish.getPrice());
    }

    @Override
    public boolean validate(Dish dish, Map<String, String> errors) {
        boolean result = true;
        if (!validateType(dish.getType())) {
            result = false;
            errors.put("type", JspMessage.WRONG_DISH_TYPE);
        }
        if(!validateName(dish.getName())){
            result = false;
            errors.put("name", JspMessage.WRONG_DISH_NAME);
        }
        if(!validatePrice(dish.getPrice())){
            result = false;
            errors.put("price", JspMessage.WRONG_DISH_PRICE);
        }
        return result;
    }

    private boolean validateType(Dish.DishType dishType) {
        return dishType != null;
    }

    private boolean validatePrice(double price) {
        return price > 0;
    }

    private boolean validateName(String name) {
        name = name.replaceAll("[ ]{2,}", "");
        Pattern pattern = Pattern.compile("(^[А-Я])[-а-я ]");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
