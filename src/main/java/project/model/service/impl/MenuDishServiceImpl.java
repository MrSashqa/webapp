package project.model.service.impl;

import project.model.dao.factory.DAOFactory;
import project.model.dao.MenuDishDAO;
import project.model.dao.connection.ConnectionFactory;
import project.model.dao.connection.ConnectionWrapper;
import project.model.entity.Dish;
import project.model.entity.Menu;
import project.model.service.MenuDishService;


public class MenuDishServiceImpl implements MenuDishService {
    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    public MenuDishServiceImpl(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    @Override
    public Dish.DishType[] getAllDishTypes() {
        return Dish.DishType.values();
    }

    @Override
    public Menu getMenu() {
        Menu menu;
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            MenuDishDAO menuDishDAO = daoFactory.getMenuDishDAO(connection);
            menu = menuDishDAO.getMenu();
        }
        return menu;
    }

    @Override
    public void insertDish(Dish dish) {
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            MenuDishDAO menuDishDAO = daoFactory.getMenuDishDAO(connection);
            menuDishDAO.insert(dish);
        }
    }

    @Override
    public void removeDish(int id) {
        try (ConnectionWrapper connection = connectionFactory.getConnection()) {
            MenuDishDAO menuDishDAO = daoFactory.getMenuDishDAO(connection);
            menuDishDAO.delete(id);
        }
    }
}
