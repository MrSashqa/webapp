package project.controller.command;

import org.apache.log4j.Logger;
import project.controller.wrapper.RequestWrapper;

public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);

    private CommandFactory() {
    }

    public static ActionCommand defineCommand(RequestWrapper requestWrapper) {

        String command = requestWrapper.getRequestParameter("command").toUpperCase();
        LOGGER.debug("COMMAND :" + command);
        return CommandEnum.valueOf(command).getCommand();
    }

}
