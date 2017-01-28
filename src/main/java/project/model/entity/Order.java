package project.model.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private Client client;
    private Map<Dish, Integer> orderContent = new HashMap<>();
    private OrderStatus status;
    private Timestamp dateCreated;

    public enum OrderStatus {
        NEW, PROCESSED, CLOSED, DENIED
    }

    public Order(int id, Client client, OrderStatus status, Timestamp dateCreated) {
        this.id = id;
        this.client = client;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Order(int id, Client client, Map<Dish, Integer> orderContent, OrderStatus status, Timestamp dateCreated) {
        this.id = id;
        this.client = client;
        this.orderContent = orderContent;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getClientId() {
        return client.getId();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<Dish, Integer> getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(Map<Dish, Integer> orderContent) {
        this.orderContent = orderContent;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = OrderStatus.valueOf(status);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", orderContent=" + orderContent +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                '}';
    }

}
