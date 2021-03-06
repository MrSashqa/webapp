package project.controller.command.impl;

import project.controller.command.Action;
import project.controller.command.Action.ActionType;
import project.controller.command.ActionCommand;
import project.controller.wrapper.RequestWrapper;
import project.util.Pages;

public class RegisterPageCommand extends ActionCommand {

    @Override
    public Action execute(RequestWrapper request) {
        return new Action(Pages.REGISTER, ActionType.FORWARD);
    }
}
