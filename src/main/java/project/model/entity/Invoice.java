package project.model.entity;


import java.sql.Timestamp;

public class Invoice {
    private int id;
    private Order order;
    private Timestamp dateCreated;
    private boolean paid;
    private double cost;
    private int discount;
    private double charge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public Invoice(int id, Order order, Timestamp dateCreated, boolean paid, double cost, int discount, double charge) {
        this.id = id;
        this.order = order;
        this.dateCreated = dateCreated;
        this.paid = paid;
        this.cost = cost;
        this.discount = discount;
        this.charge = charge;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getCost() {
        return cost;
    }


    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", order=" + order +
                ", dateCreated=" + dateCreated +
                ", paid=" + paid +
                ", cost=" + cost +
                ", discount=" + discount +
                ", charge=" + charge +
                '}';
    }
}