package project.model.service;


public interface ServiceFactory {

    UserService getUserService();

    ClientService getClientService();

    MenuDishService getMenuDishService();

    OrderService gerOrderService();
}
