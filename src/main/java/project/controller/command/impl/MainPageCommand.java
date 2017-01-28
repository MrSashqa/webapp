package project.controller.command.impl;

import project.controller.command.ActionCommand;
import project.controller.command.CommandEnum;
import project.controller.wrapper.RequestWrapper;
import project.util.Roles;


public class MainPageCommand extends ActionCommand {
    @Override
    public String execute(RequestWrapper request) {
        String role = (String) request.getAttributeFromSession(Roles.ROLE);
        if (role.equals(Roles.ADMIN)) {
            return CommandEnum.ADMIN_PAGE.getCommand().execute(request);
        }
        return CommandEnum.MENU_CONTENT.getCommand().execute(request);
    }
}
