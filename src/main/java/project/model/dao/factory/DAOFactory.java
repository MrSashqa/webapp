package project.model.dao.factory;


import project.model.dao.*;
import project.model.dao.connection.ConnectionWrapper;

public interface DAOFactory {

    UserDAO getUserDAO(ConnectionWrapper connectionWrapper);

    ClientDAO getClientDAO(ConnectionWrapper connectionWrapper);

    MenuDishDAO getMenuDishDAO(ConnectionWrapper connectionWrapper);

    OrderDAO getOrderDAO(ConnectionWrapper connectionWrapper);

    InvoiceDAO getInvoiceDAO(ConnectionWrapper connectionWrapper);

}
