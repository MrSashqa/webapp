package project.controller.command.impl;

import project.controller.command.ActionCommand;
import project.controller.command.CommandEnum;
import project.model.service.security.PasswordHasher;
import project.controller.validator.impl.ClientValidator;
import project.controller.validator.impl.UserValidator;
import project.controller.wrapper.RequestWrapper;
import project.controller.command.CommandUtil;
import project.model.entity.Client;
import project.model.entity.User;
import project.model.service.ClientService;
import project.model.service.UserService;
import project.util.JspMessage;
import project.util.Pages;

import java.util.HashMap;
import java.util.Map;

public class RegisterCommand extends ActionCommand {
    @Override


    public String execute(RequestWrapper request) {
        CommandUtil requestWrapperUtil = new CommandUtil();
        ClientValidator clientValidator = new ClientValidator();
        UserValidator userValidator = new UserValidator();
        Map<String, String> errors = new HashMap<>();
        User user = requestWrapperUtil.retrieveUser(request);
        Client client = requestWrapperUtil.retrieveClient(request);
        if (!userValidator.validate(user, errors) || !clientValidator.validate(client, errors)) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            request.setAttribute("client", client);
            return Pages.REGISTER;
        }
        UserService userService = serviceFactory.getUserService();
        ClientService clientService = serviceFactory.getClientService();
        if (userService.insert(user)) {
            client.setId(user.getId());
            clientService.insert(client);
            request.setAttribute("user", user);

            return CommandEnum.LOGIN.getCommand().execute(request);
        }
        errors.put("login", JspMessage.ERROR_LOGIN_EXISTS);
        request.setAttribute("user", user);
        request.setAttribute("client", client);
        request.setAttribute("errors", errors);
        return Pages.REGISTER;
    }
}
