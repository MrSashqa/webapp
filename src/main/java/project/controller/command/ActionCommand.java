package project.controller.command;

import project.controller.wrapper.RequestWrapper;
import project.model.service.ServiceFactory;
import project.model.service.impl.ServiceFactoryImpl;

public abstract class ActionCommand {

    protected ServiceFactory serviceFactory;

    public ActionCommand() {
        this.serviceFactory = ServiceFactoryImpl.getInstance();
    }

    public abstract Action execute(RequestWrapper request);


}
