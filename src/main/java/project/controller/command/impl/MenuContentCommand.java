package project.controller.command.impl;

import project.controller.command.Action;
import project.controller.command.Action.ActionType;
import project.controller.command.ActionCommand;
import project.controller.wrapper.RequestWrapper;
import project.model.entity.Dish;
import project.model.entity.Dish.DishType;
import project.model.service.MenuDishService;
import project.util.Pages;
import project.util.Roles;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class MenuContentCommand extends ActionCommand {

    @Override
    public Action execute(RequestWrapper request) {
        MenuDishService menuDishService = serviceFactory.getMenuDishService();
        EnumMap<DishType, List<Dish>> menu = menuDishService.getMenu().getContent();
        String role = (String) request.getAttributeFromSession(Roles.ROLE);
        if (role.equals(Roles.ADMIN)) {
            DishType[] dishTypes = menuDishService.getAllDishTypes();
            request.setAttribute("dishTypes", Arrays.asList(dishTypes));
            request.setAttribute("menu", menu);
            return new Action(Pages.ADMIN_DISHES, ActionType.FORWARD);
        } else {
            request.setAttribute("menu", menu);
            return new Action(Pages.MAIN, ActionType.FORWARD);
        }
    }
}
