package project.model.dao.impl;

import org.apache.log4j.Logger;
import project.model.dao.*;
import project.model.dao.exception.DAOException;
import project.model.entity.*;
import project.model.entity.Order.*;
import project.util.LoggerMessage;

import java.sql.*;
import java.util.*;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private Connection connection;
    private MenuDishDAO menuDishDAO;
    private ClientDAO clientDAO;
    private static final String DATE_CREATED = "dateCreated";
    private static final String ORDER_ID = "id";
    private static final String CLIENT_ID = "clientId";
    private static final String ORDER_STATUS = "orderStatus";


    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
        this.menuDishDAO = new MenuDishDAOImpl(connection);
        this.clientDAO = new ClientDAOImpl(connection);
    }


    @Override
    public Order getById(int id) {
        Order order = new Order();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT clientId, orderStatus,dateCreated FROM orders WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                order = getOrder(resultSet, id, null, null);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_ORDER);
            throw new DAOException(LoggerMessage.ERROR_GET_ORDER, e);
        }
        return order;
    }

    private void insertOrderContent(Order order) throws SQLException {
        Map<Dish, Integer> orderContent = order.getOrderContent();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO order_lines(orderId, dishId, quantity) VALUES (?,?,?)")) {
            for (Map.Entry<Dish, Integer> entry : orderContent.entrySet()) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, entry.getKey().getId());
                preparedStatement.setInt(3, entry.getValue());
                preparedStatement.addBatch();
            }
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void insertOrder(Order order) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders(clientId) VALUES(?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getClientId());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                order.setId(resultSet.getInt(1));
                insertOrderContent(order);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INSERT_ORDER);
            throw new DAOException(LoggerMessage.ERROR_INSERT_ORDER, e);
        }
    }


    @Override
    public void updateStatus(Order order) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET orderStatus=? WHERE id=?")) {
            preparedStatement.setString(1, order.getStatus().name());
            preparedStatement.setInt(2, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_UPDATE_ORDER_STATUS);
            throw new DAOException(LoggerMessage.ERROR_UPDATE_ORDER_STATUS, e);
        }
    }

    @Override
    public Optional<Order> getByClientIdAndStatus(int clientId, OrderStatus status) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, dateCreated  FROM orders WHERE clientId=?  AND orderStatus=?")) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.setString(2, status.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return Optional.of(getOrder(resultSet, null, clientId, status));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_ORDER_BY_STATUS);
            throw new DAOException(LoggerMessage.ERROR_GET_ORDER_BY_STATUS, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAllByStatus(OrderStatus status) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, dateCreated,clientId  FROM orders WHERE orderStatus=?")) {
            preparedStatement.setString(1, status.name());
            return getOrders(preparedStatement.executeQuery(), status);
        } catch (SQLException e) {
            throw new DAOException(LoggerMessage.ERROR_GET_ORDERS_BY_STATUS, e);
        }
    }

    private Order getOrder(ResultSet resultSet, Integer orderId, Integer clientId, OrderStatus orderStatus) throws SQLException {
        int id = orderId == null ? resultSet.getInt(ORDER_ID) : orderId;
        int client = clientId == null ? resultSet.getInt(CLIENT_ID) : clientId;
        OrderStatus status = orderStatus == null ? OrderStatus.valueOf(resultSet.getString(ORDER_STATUS)) : orderStatus;
        Order order = new Order();
        order.setId(id);
        order.setClient(clientDAO.getById(client).get());
        order.setStatus(status);
        order.setDateCreated(resultSet.getTimestamp(DATE_CREATED));
        order.setOrderContent(menuDishDAO.getAllByOrderId(id));
        return order;
    }

    private List<Order> getOrders(ResultSet resultSet, OrderStatus status) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            orders.add(getOrder(resultSet, null, null, status));
        }
        return orders;
    }

    public static void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "orderId INT," +
                    "orderStatus ENUM('NEW', 'PROCESSED', 'DENIED', 'CLOSED') NOT NULL DEFAULT 'NEW'," +
                    "dateCreated TIMESTAMP DEFAULT current_timestamp," +
                    "FOREIGN KEY (orderId) REFERENCES clients(id))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS order_lines(" +
                    "orderId INT," +
                    "dishId INT," +
                    "quantity INT," +
                    "FOREIGN KEY (orderId) REFERENCES orders(id)," +
                    "FOREIGN KEY (dishId) REFERENCES menu(dishId))");
            LOGGER.info(LoggerMessage.SUCCESS_ORDER_DAO_INIT);
        } catch (SQLException e) {
            LOGGER.info(LoggerMessage.SUCCESS_ORDER_DAO_INIT);
            throw new DAOException(LoggerMessage.ERROR_ORDER_DAO_INIT, e);
        }
    }
}

