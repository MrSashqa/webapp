package project.controller.command.impl;


import project.controller.command.ActionCommand;
import project.controller.command.CommandEnum;
import project.controller.command.CommandUtil;
import project.controller.validator.impl.OrderValidator;
import project.controller.wrapper.RequestWrapper;
import project.model.entity.Order;
import project.model.service.OrderService;
import java.util.HashMap;
import java.util.Map;


public class CreateOrderCommand extends ActionCommand {

    @Override
    public String execute(RequestWrapper request) {

        Order order = CommandUtil.retrieveOrder(request);
        OrderValidator validator = new OrderValidator();
        Map<String, String> errors = new HashMap<>();
        if (validator.validate(order, errors)) {
            OrderService orderService = serviceFactory.gerOrderService();
            orderService.insert(order);
            request.setAttribute("order", order);
            return CommandEnum.WAITING_PAGE.getCommand().execute(request);
        }
        request.setAttribute("errors", errors);
        return CommandEnum.MENU_CONTENT.getCommand().execute(request);
    }
}
