package project.controller.command;

import org.apache.log4j.Logger;
import project.controller.command.impl.*;
import project.util.UrlHolder;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
    private static final Map<String, ActionCommand> urlMapping;

    private CommandFactory() {
    }

    static {
        urlMapping = new HashMap<>();
        urlMapping.put(UrlHolder.INDEX, new LoginPageCommand());
        urlMapping.put(UrlHolder.LOGIN, new LoginCommand());
        urlMapping.put(UrlHolder.CLIENT_MAIN, new MenuContentCommand());
        urlMapping.put(UrlHolder.CLIENT_ORDER, new CreateOrderCommand());
        urlMapping.put(UrlHolder.ADMIN_MAIN, new AdminPageCommand());
        urlMapping.put(UrlHolder.ADMIN_MAIN_MENU, new MenuContentCommand());
        urlMapping.put(UrlHolder.REGISTER_PAGE, new RegisterPageCommand());
        urlMapping.put(UrlHolder.REGISTER_USER, new RegisterUserCommand());
        urlMapping.put(UrlHolder.ORDER_IN_PROCESS, new WaitingPageCommand());

    }

    public static ActionCommand defineCommand(String cmd) {
        return urlMapping.get(cmd);
    }

}
