package project.controller.command.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.log4j.Logger;
import project.controller.command.Action;
import project.controller.command.Action.ActionType;
import project.controller.command.ActionCommand;
import project.util.JspMessage;
import project.util.Pages;
import project.controller.wrapper.RequestWrapper;
import project.controller.command.CommandUtil;
import project.model.entity.User;
import project.model.service.UserService;
import project.util.Roles;
import project.util.UrlHolder;

public class LoginCommand extends ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private UserService userService = serviceFactory.getUserService();

    @Override
    public Action execute(RequestWrapper request) {
        User user = CommandUtil.retrieveUser(request);
        Map<String, String> errors = new HashMap<>();
        Optional<User> userOptional = userService.getByLogin(user.getLogin());
        if (!userOptional.isPresent()) {
            errors.put("login", JspMessage.ERROR_LOGIN_NOT_EXIST);
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            return new Action(Pages.LOGIN, ActionType.FORWARD);
        }
        if (!userService.authenticate(user)) {
            user.setPassword("");
            errors.put("password", JspMessage.WRONG_PASSWORD);
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
            return new Action(Pages.LOGIN, ActionType.FORWARD);
        }
        request.setAttributeToSession(Roles.ROLE, userOptional.get().isAdmin() ? Roles.ADMIN : Roles.CLIENT);
        request.setAttributeToSession("user", userOptional.get());

        return userOptional.get().isAdmin() ?
                new Action(UrlHolder.ADMIN_MAIN, ActionType.REDIRECT) :
                new Action(UrlHolder.CLIENT_MAIN, ActionType.REDIRECT);
    }


}

