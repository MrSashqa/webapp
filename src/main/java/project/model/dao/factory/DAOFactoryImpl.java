package project.model.dao.factory;

import project.model.dao.*;
import project.model.dao.connection.ConnectionFactory;
import project.model.dao.connection.ConnectionWrapper;
import project.model.dao.impl.*;


public class DAOFactoryImpl implements DAOFactory {
    private static final DAOFactoryImpl instance = new DAOFactoryImpl();

    private DAOFactoryImpl() {
        try (ConnectionWrapper connection = ConnectionFactory.getInstance().getConnection()) {
            UserDAOImpl.init(connection.getConnection());
            ClientDAOImpl.init(connection.getConnection());
            MenuDishDAOImpl.init(connection.getConnection());
            OrderDAOImpl.init(connection.getConnection());
            InvoiceDAOImpl.init(connection.getConnection());
        }
    }


    public static DAOFactoryImpl getInstance() {
        return instance;
    }

    @Override
    public UserDAO getUserDAO(ConnectionWrapper connectionWrapper) {
        return new UserDAOImpl(connectionWrapper.getConnection());
    }

    @Override
    public ClientDAO getClientDAO(ConnectionWrapper connectionWrapper) {
        return new ClientDAOImpl(connectionWrapper.getConnection());
    }

    @Override
    public MenuDishDAO getMenuDishDAO(ConnectionWrapper connectionWrapper) {
        return new MenuDishDAOImpl(connectionWrapper.getConnection());
    }

    @Override
    public OrderDAO getOrderDAO(ConnectionWrapper connectionWrapper) {
        return new OrderDAOImpl(connectionWrapper.getConnection());
    }

    @Override
    public InvoiceDAO getInvoiceDAO(ConnectionWrapper connectionWrapper){
        return new InvoiceDAOImpl(connectionWrapper.getConnection());
    }

}
