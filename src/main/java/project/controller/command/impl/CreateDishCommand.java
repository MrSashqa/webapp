package project.controller.command.impl;

import project.controller.command.ActionCommand;
import project.controller.command.CommandEnum;
import project.controller.validator.impl.DishValidator;
import project.controller.wrapper.RequestWrapper;
import project.controller.command.CommandUtil;
import project.model.entity.Dish;
import project.model.service.MenuDishService;

import java.util.HashMap;
import java.util.Map;

public class CreateDishCommand extends ActionCommand {


    @Override
    public String execute(RequestWrapper request) {
        Dish dish = CommandUtil.retrieveDish(request);
        DishValidator dishValidator = new DishValidator();
        Map<String, String> errors = new HashMap<>();

        if (!dishValidator.validate(dish, errors)) {
            request.setAttribute("errors", errors);
            request.setAttribute("dish", dish);
            return CommandEnum.MENU_CONTENT.getCommand().execute(request);
        }
        MenuDishService menuDishService = serviceFactory.getMenuDishService();
        menuDishService.insertDish(dish);
        System.out.println();
        return CommandEnum.MENU_CONTENT.getCommand().execute(request);
    }
}
