package project.controller.command.impl;

import org.apache.log4j.Logger;
import project.controller.command.Action;
import project.controller.command.Action.ActionType;
import project.controller.command.ActionCommand;
import project.controller.wrapper.RequestWrapper;
import project.model.entity.Order;
import project.model.service.OrderService;
import project.util.Pages;

import java.util.List;

public class AdminPageCommand extends ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(AdminPageCommand.class);

    @Override
    public Action execute(RequestWrapper request) {
        LOGGER.debug("ADMIN PAGE COMMAND");
        OrderService orderService = serviceFactory.gerOrderService();
        List<Order> orders = orderService.getAllByStatus(Order.OrderStatus.NEW);
        request.setAttribute("orders", orders);
        return new Action(Pages.ADMIN, ActionType.FORWARD);
    }
}
