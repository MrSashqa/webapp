package project.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.apache.log4j.Logger;
import project.model.dao.ClientDAO;
import project.model.dao.exception.DAOException;
import project.model.entity.Client;
import project.util.LoggerMessage;

public class ClientDAOImpl implements ClientDAO {
    private static final Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String E_MAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";

    private Connection connection;


    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }


    public static void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS clients(id INT," +
                    "firstName VARCHAR(32)," +
                    "lastName VARCHAR(32)," +
                    "phonenumber VARCHAR(16)," +
                    "email VARCHAR(32)," +
                    "FOREIGN KEY (id) REFERENCES users(id))");
            LOGGER.info(LoggerMessage.SUCCESS_INIT_CLIENT_DAO);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INIT_CLIENT_DAO);
            throw new DAOException(LoggerMessage.ERROR_INIT_CLIENT_DAO, e);
        }
    }


    @Override
    public Optional<Client> getById(int id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM clients WHERE id=? LIMIT 1")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return Optional.of(getClient(resultSet, id));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_CLIENT);
            throw new DAOException(LoggerMessage.ERROR_GET_CLIENT, e);
        }
        return Optional.empty();
    }

    @Override
    public void insert(Client client) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO clients(id, firstName, lastName, email, phoneNumber) VALUES(?,?,?,?,?)")) {
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getLastName());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INSERT_CLIENT);
            throw new DAOException(LoggerMessage.ERROR_INSERT_CLIENT, e);
        }
    }

    private Client getClient(ResultSet resultSet, Integer id) throws SQLException {
        Client client = new Client();
        client.setId(id == null ? resultSet.getInt(ID) : id);
        client.setFirstName(resultSet.getString(FIRST_NAME));
        client.setLastName(resultSet.getString(LAST_NAME));
        client.setPhoneNumber((resultSet.getString(PHONE_NUMBER)));
        client.setEmail((resultSet.getString(E_MAIL)));
        return client;
    }

}
