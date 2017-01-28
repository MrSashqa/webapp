package project.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import project.model.dao.UserDAO;
import project.model.dao.exception.DAOException;
import project.model.entity.User;
import org.apache.log4j.Logger;
import project.util.LoggerMessage;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private Connection connection;
    private final static String LOGIN = "login";
    private final static String PASS = "pass";
    private final static String IS_ADMIN = "isAdmin";
    private final static String USER_ID = "id";

    public UserDAOImpl(Connection connection) {
        super();
        this.connection = connection;
    }


    public static void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + " login VARCHAR(32) UNIQUE,"
                    + " pass VARCHAR(32),"
                    + " isAdmin BOOLEAN)");
            LOGGER.info(LoggerMessage.SUCCESS_INIT);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INIT);
            throw new DAOException(LoggerMessage.ERROR_INIT, e);
        }
    }


    @Override
    public Optional<User> getByLogin(String login) {
        Optional<User> userOptional;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM users WHERE login =? LIMIT 1 ")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            userOptional = Optional.ofNullable(getUser(resultSet));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_BY_LOGIN);
            throw new DAOException(LoggerMessage.ERROR_GET_BY_LOGIN, e);
        }
        return userOptional;
    }

    @Override
    public void insert(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users(login, pass, isAdmin) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isAdmin());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INSERT_USER);
            throw new DAOException(LoggerMessage.ERROR_INSERT_USER, e);
        }
    }


    private User getUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASS));
            user.setId(resultSet.getInt(USER_ID));
            user.setAdmin(resultSet.getBoolean(IS_ADMIN));
            return user;
        }
        return null;
    }

}
