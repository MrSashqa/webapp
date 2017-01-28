package project.controller.command;

import project.controller.command.impl.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    }, REGISTER {
        {
            this.command = new RegisterCommand();
        }
    }, MENU_CONTENT {
        {
            this.command = new MenuContentCommand();
        }
    }, ADMIN_PAGE {
        {
            this.command = new AdminPageCommand();
        }
    }, CREATE_DISH {
        {
            this.command = new CreateDishCommand();
        }
    }, DELETE_DISH {
        {
            this.command = new DeleteDishCommand();
        }
    }, CREATE_ORDER {
        {
            this.command = new CreateOrderCommand();
        }
    }, WAITING_PAGE {
        {
            this.command = new WaitingPageCommand();
        }
    };

    protected ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
