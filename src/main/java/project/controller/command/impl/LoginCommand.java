package project.controller.command.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import project.controller.command.ActionCommand;
import project.controller.command.CommandEnum;
import project.model.service.security.PasswordHasher;
import project.util.JspMessage;
import project.util.Pages;
import project.controller.validator.impl.UserValidator;
import project.controller.wrapper.RequestWrapper;
import project.controller.command.CommandUtil;
import project.model.entity.User;
import project.model.service.UserService;
import project.util.Roles;

public class LoginCommand extends ActionCommand {


    @Override
    public String execute(RequestWrapper request) {
        User user = CommandUtil.retrieveUser(request);
        Map<String, String> errors = new HashMap<>();
        boolean valid = (new UserValidator()).validate(user);
        if (!valid) {
            errors.put("login", JspMessage.ERROR_LOGIN_NOT_EXIST);
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            return Pages.LOGIN;
        }

        UserService userService = serviceFactory.getUserService();
        Optional<User> userOptional = userService.getByLogin(user.getLogin());
        if (!userOptional.isPresent()) {
            errors.put("login", JspMessage.ERROR_LOGIN_NOT_EXIST);
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            return Pages.LOGIN;
        }

        if (!userService.authenticate(user)) {
            user.setPassword("");
            errors.put("password", JspMessage.WRONG_PASSWORD);
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            return Pages.LOGIN;
        }
        System.out.println(userOptional.get().isAdmin());
        if (userOptional.get().isAdmin()) {
            request.setAttributeToSession(Roles.ROLE, Roles.ADMIN);
            request.setAttributeToSession("user", user);
            return CommandEnum.ADMIN_PAGE.getCommand().execute(request);
        } else {
            user.setId(userOptional.get().getId());
            request.setAttributeToSession(Roles.ROLE, Roles.CLIENT);
            request.setAttributeToSession("user", user);
            return CommandEnum.MENU_CONTENT.getCommand().execute(request);
        }
    }
}

