package project.model.dao;


import project.model.entity.Dish;
import project.model.entity.Menu;

import java.util.Map;

public interface MenuDishDAO {

    void insert(Dish dish);

    void delete(int id);

    Menu getMenu();

    Map<Dish,Integer> getAllByOrderId(int id);
}
