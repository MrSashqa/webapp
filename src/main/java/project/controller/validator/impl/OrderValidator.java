package project.controller.validator.impl;


import project.controller.validator.Validator;
import project.model.entity.Client;
import project.model.entity.Dish;
import project.model.entity.Order;
import project.util.JspMessage;

import java.util.Map;

public class OrderValidator implements Validator<Order> {


    @Override
    public boolean validate(Order order) {
        return validateContent(order.getOrderContent());
    }

    @Override
    public boolean validate(Order order, Map<String, String> errors) {
        boolean result = true;
        if (!validateContent(order.getOrderContent())) {
            result = false;
            errors.put("order", JspMessage.WRONG_ORDER);
        }
        return result;
    }


    private boolean validateContent(Map<Dish, Integer> map) {
        if (map.keySet().size() != map.values().size()) {
            return false;
        }
        for (Map.Entry<Dish, Integer> entry : map.entrySet()) {
            if (entry.getKey().getId() <= 0 || entry.getValue() <= 0) {
                return false;
            }
        }
        return true;
    }


}
