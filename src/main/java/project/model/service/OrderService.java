package project.model.service;


import project.model.entity.Order;
import project.model.entity.Order.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void insert(Order order);

    void update(Order order);

    Optional<Order> getByStatusAndClientId(int id, OrderStatus status);

    List<Order> getAllByStatus(OrderStatus status);


}
