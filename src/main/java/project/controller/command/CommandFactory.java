package project.controller.command;

import project.controller.wrapper.RequestWrapper;

public class CommandFactory {
    private CommandFactory() {
    }

    public static ActionCommand defineCommand(RequestWrapper requestWrapper) {

        String command = requestWrapper.getRequestParameter("command").toUpperCase();
        System.out.println(command);
        return CommandEnum.valueOf(command).getCommand();
    }

}
