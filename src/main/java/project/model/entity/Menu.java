package project.model.entity;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Menu {

    private EnumMap<Dish.DishType, List<Dish>> content = new EnumMap<>(Dish.DishType.class);


    public void addDish(Dish dish) {
        if (content.containsKey(dish.getType())) {
            List<Dish> dishes = content.get(dish.getType());
            dishes.add(dish);
        } else {
            List<Dish> dishes = new ArrayList<>();
            dishes.add(dish);
            content.put(dish.getType(), dishes);
        }
    }

    public EnumMap<Dish.DishType, List<Dish>> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "content=" + content +
                '}';
    }
}
