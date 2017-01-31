package project.controller.command.impl;


import project.controller.command.Action;
import project.controller.command.ActionCommand;
import project.controller.wrapper.RequestWrapper;
import project.util.Pages;

public class WaitingPageCommand extends ActionCommand {

    @Override
    public Action execute(RequestWrapper request) {


        return new Action(Pages.WAITING_PAGE, Action.ActionType.FORWARD);

    }
}
