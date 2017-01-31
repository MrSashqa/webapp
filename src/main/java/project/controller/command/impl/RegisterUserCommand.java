package project.controller.command.impl;

import project.controller.command.Action;
import project.controller.command.Action.ActionType;
import project.controller.command.ActionCommand;
import project.controller.command.CommandFactory;
import project.controller.validator.impl.ClientValidator;
import project.controller.validator.impl.UserValidator;
import project.controller.wrapper.RequestWrapper;
import project.controller.command.CommandUtil;
import project.model.entity.Client;
import project.model.entity.User;
import project.model.service.ClientService;
import project.model.service.UserService;
import project.util.*;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserCommand extends ActionCommand {
    @Override

    public Action execute(RequestWrapper request) {
        ClientValidator clientValidator = new ClientValidator();
        UserValidator userValidator = new UserValidator();
        Map<String, String> errors = new HashMap<>();
        User user = CommandUtil.retrieveUser(request);
        Client client = CommandUtil.retrieveClient(request);
        if (!userValidator.validate(user, errors) || !clientValidator.validate(client, errors)) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            request.setAttribute("client", client);
            return new Action(Pages.REGISTER, ActionType.FORWARD);
        }
        UserService userService = serviceFactory.getUserService();
        ClientService clientService = serviceFactory.getClientService();
        if (userService.insert(user)) {
            client.setId(user.getId());
            clientService.insert(client);
            request.setAttribute("user", user);
            return new Action(UrlHolder.CLIENT_MAIN, ActionType.REDIRECT);
        }
        errors.put("login", JspMessage.ERROR_LOGIN_EXISTS);
        request.setAttribute("user", user);
        request.setAttribute("client", client);
        request.setAttribute("errors", errors);
        return new Action(Pages.REGISTER, ActionType.FORWARD);
    }
}
