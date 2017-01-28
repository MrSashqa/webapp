package project.model.service.impl;

import project.model.dao.factory.DAOFactory;
import project.model.dao.connection.ConnectionFactory;
import project.model.dao.factory.DAOFactoryImpl;
import project.model.service.*;


public class ServiceFactoryImpl implements ServiceFactory {

    private static final ServiceFactoryImpl instance = new ServiceFactoryImpl();
    private DAOFactory daoFactory = DAOFactoryImpl.getInstance();
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    public static ServiceFactory getInstance() {
        return instance;
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl(connectionFactory, daoFactory);
    }

    @Override
    public ClientService getClientService() {
        return new ClientServiceImpl(connectionFactory, daoFactory);
    }

    @Override
    public MenuDishService getMenuDishService() {
        return new MenuDishServiceImpl(connectionFactory, daoFactory);
    }

    @Override
    public OrderService gerOrderService() {
        return new OrderServiceImpl(connectionFactory, daoFactory);
    }

}
