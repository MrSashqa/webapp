package project.model.service;


import project.model.entity.Dish;
import project.model.entity.Menu;


public interface MenuDishService {

    Dish.DishType[] getAllDishTypes();

    Menu getMenu();

    void  insertDish(Dish dish);

    void removeDish(int id);

}
