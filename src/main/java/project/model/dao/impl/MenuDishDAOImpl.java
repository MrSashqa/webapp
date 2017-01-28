package project.model.dao.impl;

import org.apache.log4j.Logger;
import project.model.dao.MenuDishDAO;
import project.model.dao.exception.DAOException;
import project.model.entity.Dish;
import project.model.entity.Menu;
import project.util.LoggerMessage;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class MenuDishDAOImpl implements MenuDishDAO {
    private static final Logger LOGGER = Logger.getLogger(MenuDishDAOImpl.class);
    private static final String DISH_ID = "dishId";
    private static final String DISH_NAME = "dishId";
    private static final String PRICE = "price";
    private static final String DISH_TYPE = "dishType";
    private static final String QUANTITY = "quantity";

    private Connection connection;


    public MenuDishDAOImpl(Connection connection) {
        this.connection = connection;
    }


    public static void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS menu(" +
                    "dishId INT PRIMARY KEY AUTO_INCREMENT," +
                    "dishType ENUM('MAIN_DISH','SIDE_DISH','SOUP', 'STARTER','DESSERT', 'SAUCE'), " +
                    "price DECIMAL(8,2), " +
                    "dishName VARCHAR(100))");
            LOGGER.info(LoggerMessage.SUCCESS_INIT_MENU_DISH_DAO);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INIT_MENU_DISH_DAO);
            throw new DAOException(LoggerMessage.ERROR_INIT_MENU_DISH_DAO, e);
        }
    }

    @Override
    public void insert(Dish dish) {
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("INSERT INTO menu(dishType,dishName,price) VALUE (?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, dish.getType().name());
            preparedStatement.setString(2, dish.getName());
            preparedStatement.setDouble(3, dish.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            dish.setId(resultSet.getInt(1));
            LOGGER.info(LoggerMessage.SUCCESS_INSERT_DISH + dish);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INSERT_DISH);
            throw new DAOException(LoggerMessage.ERROR_INSERT_DISH, e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM menu WHERE dishId=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_DELETE_DISH);
            throw new DAOException(LoggerMessage.ERROR_DELETE_DISH, e);
        }
    }

    @Override
    public Menu getMenu() {
        try (Statement statement = connection.createStatement()) {
            return getMenu(statement.executeQuery("SELECT dishId, dishType,price,dishName FROM menu"));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_MENU);
            throw new DAOException(LoggerMessage.ERROR_GET_MENU, e);
        }
    }

    @Override
    public Map<Dish, Integer> getAllByOrderId(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT quantity, menu.dishId, dishType, price, dishName " +
                "FROM menu  " +
                "INNER JOIN order_lines ON order_lines.dishId=menu.dishId " +
                "INNER JOIN orders ON orders.id = order_lines.dishId WHERE clientId=?")) {
            preparedStatement.setInt(1, id);
            return getDishToQuantity(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_DISHES_BY_ORDER);
            throw new DAOException(LoggerMessage.ERROR_GET_DISHES_BY_ORDER, e);
        }
    }

    private Dish getDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt(DISH_ID));
        dish.setPrice(resultSet.getDouble(PRICE));
        dish.setType(resultSet.getString(DISH_TYPE));
        dish.setName(resultSet.getString(DISH_NAME));
        return dish;
    }


    private Menu getMenu(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();
        while (resultSet.next()) {
            menu.addDish(getDish(resultSet));
        }
        return menu;
    }


    private Map<Dish, Integer> getDishToQuantity(ResultSet resultSet) throws SQLException {
        Map<Dish, Integer> dishIntegerMap = new HashMap<>();
        while (resultSet.next()) {
            dishIntegerMap.put(getDish(resultSet), resultSet.getInt(QUANTITY));
        }
        return dishIntegerMap;
    }
}
