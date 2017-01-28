package project.model.service.impl;

import project.model.dao.factory.DAOFactory;
import project.model.dao.OrderDAO;
import project.model.dao.connection.ConnectionFactory;
import project.model.dao.connection.ConnectionWrapper;
import project.model.entity.Order;
import project.model.service.OrderService;

import java.util.List;
import java.util.Optional;


public class OrderServiceImpl implements OrderService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    public OrderServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    @Override
    public void insert(Order order) {
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            OrderDAO orderDao = daoFactory.getOrderDAO(connection);
            connection.beginTransaction();
            orderDao.insertOrder(order);
            connection.commit();
        }
    }

    @Override
    public void update(Order purchaseOrder) {
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            OrderDAO orderDao = daoFactory.getOrderDAO(connection);
            orderDao.updateStatus(purchaseOrder);
        }
    }

    @Override
    public Optional<Order> getByStatusAndClientId(int id, Order.OrderStatus status) {
        Optional<Order> orderOptional;
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            OrderDAO orderDao = daoFactory.getOrderDAO(connection);
            orderOptional = orderDao.getByClientIdAndStatus(id, status);
            if (orderOptional.isPresent())
                orderOptional.get().setOrderContent(daoFactory.getMenuDishDAO(connection).getAllByOrderId(id));
        }
        return orderOptional;
    }

    @Override
    public List<Order> getAllByStatus(Order.OrderStatus status) {
        List<Order> orders;
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            OrderDAO orderDao = daoFactory.getOrderDAO(connection);
            orders = orderDao.getAllByStatus(status);
        }
        return orders;
    }
}
