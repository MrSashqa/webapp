package project.controller.command.impl;


import project.controller.command.ActionCommand;
import project.controller.wrapper.RequestWrapper;
import project.util.Pages;

public class WaitingPageCommand extends ActionCommand{

    @Override
    public String execute(RequestWrapper request) {
        return Pages.WAITING_PAGE;
    }
}
