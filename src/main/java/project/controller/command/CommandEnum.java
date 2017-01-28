package project.controller.command;

import project.controller.command.impl.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    MAIN_PAGE(new MainPageCommand()),
    REGISTER(new RegisterCommand()),
    MENU_CONTENT(new MenuContentCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    CREATE_DISH(new CreateDishCommand()),
    DELETE_DISH(new DeleteDishCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    WAITING_PAGE(new WaitingPageCommand());


    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
