package project.model.dao;


import project.model.entity.Dish;
import project.model.entity.Order;
import project.model.entity.Order.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderDAO {


    void insertOrder(Order order);

    void updateStatus(Order order);

    Optional<Order> getByClientIdAndStatus(int id, OrderStatus status);

    Order getById(int id);

    List<Order> getAllByStatus(OrderStatus status);


}
