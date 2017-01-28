package project.model.entity;


import java.util.Objects;

public class Dish {
    private int id;
    private double price;
    private String name;
    private DishType type;

    public Dish(int id, DishType type, String dishName, double price) {
        this.id = id;
        this.price = price;
        this.name = dishName;
        this.type = type;
    }

    public Dish(DishType type, String dishName, double price) {
        this.type = type;
        this.name = dishName;
        this.price = price;
    }

    public Dish(int id) {
        this.id = id;
    }

    public Dish() {
    }

    public void setType(String dishName) {
        setType(DishType.valueOf(dishName));
    }

    public enum DishType {
        MAIN_DISH, SIDE_DISH, SOUP, STARTER, DESSERT, SAUCE;

        @Override
        public String toString() {
            return this.name().replaceAll("_", " ");
        }
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id &&
                Double.compare(dish.price, price) == 0 &&
                Objects.equals(this.name, dish.name) &&
                type == dish.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, type);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
