package project.controller.command.impl;

import project.controller.command.ActionCommand;
import project.controller.wrapper.RequestWrapper;
import project.model.entity.Order;
import project.model.service.OrderService;
import project.util.Pages;

import java.util.List;

public class AdminPageCommand extends ActionCommand{
    @Override
    public String execute(RequestWrapper request) {
        OrderService orderService = serviceFactory.gerOrderService();
        List<Order> orders = orderService.getAllByStatus(Order.OrderStatus.NEW);
        System.out.println(orders);
        request.setAttribute("orders", orders );



        return Pages.ADMIN;
    }
}
