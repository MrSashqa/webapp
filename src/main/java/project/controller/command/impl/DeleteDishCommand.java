package project.controller.command.impl;

import project.controller.command.Action;
import project.controller.command.ActionCommand;
import project.controller.command.CommandUtil;
import project.controller.wrapper.RequestWrapper;
import project.model.service.MenuDishService;
import project.util.JspMessage;

import java.util.HashMap;
import java.util.Map;


public class DeleteDishCommand extends ActionCommand {
    @Override
    public Action execute(RequestWrapper request) {
        int id = CommandUtil.retrieveDishId(request);
        if (id < 0) {
            Map<String, String> errors = new HashMap<>();
            errors.put("wrong id", JspMessage.WRONG_DISH_ID);
            request.setAttribute("errors", errors);
            return null;
        }
        MenuDishService menuDishService = serviceFactory.getMenuDishService();
        menuDishService.removeDish(id);
        return null;
    }
}
