package project.model.dao.impl;

import org.apache.log4j.Logger;
import project.model.dao.InvoiceDAO;
import project.model.dao.OrderDAO;
import project.model.dao.exception.DAOException;
import project.model.entity.Invoice;
import project.util.LoggerMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InvoiceDAOImpl implements InvoiceDAO {
    private static final Logger LOGGER = Logger.getLogger(InvoiceDAOImpl.class);
    private Connection connection;
    private OrderDAO orderDAO;

    public InvoiceDAOImpl(Connection connection) {
        this.connection = connection;
        this.orderDAO = new OrderDAOImpl(connection);
    }

    public static void init(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS invoices(id INT PRIMARY KEY AUTO_INCREMENT , " +
                    "orderId INT," +
                    "dateCreated TIMESTAMP, " +
                    "paid BOOLEAN, " +
                    "cost DOUBLE(8,2), " +
                    "charge DOUBLE(8,2), " +
                    "FOREIGN KEY (orderId) REFERENCES orders(id))");
            LOGGER.info(LoggerMessage.SUCCESS_INIT_INVOICE_DAO);
        } catch (SQLException e) {
            throw new DAOException(LoggerMessage.ERROR_INIT_INVOICE_DAO);
        }
    }


    @Override
    public void insert(Invoice invoice) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO invoices(orderId, paid, discount, cost,charge) VALUES (?,?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, invoice.getId());
            preparedStatement.setBoolean(2, invoice.isPaid());
            preparedStatement.setInt(3, invoice.getDiscount());
            preparedStatement.setDouble(4, invoice.getCost());
            preparedStatement.setDouble(5, invoice.getCharge());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                invoice.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_INSERT_INVOICE);
            throw new DAOException(LoggerMessage.ERROR_INSERT_INVOICE, e);
        }
    }

    @Override
    public void updateStatus(Invoice invoice) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE invoices SET paid=? WHERE id=?")) {
            preparedStatement.setBoolean(1, invoice.isPaid());
            preparedStatement.setInt(2, invoice.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_UPDATE_INVOICE);
            throw new DAOException(LoggerMessage.ERROR_UPDATE_INVOICE, e);
        }
    }

    @Override
    public Optional<Invoice> getByClientIdAndStatus(int id, boolean paid) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT invoices.id,orderId, cost,charge, invoices.dateCreated " +
                             "FROM invoices " +
                             "INNER JOIN orders ON orders.id=invoices.orderId " +
                             "INNER JOIN clients ON clients.id=orders.clientId " +
                             "WHERE paid=?AND clients.id=?")) {
            preparedStatement.setBoolean(1, paid);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return Optional.of(getInvoice(resultSet, id, null, paid));
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_INVOICE);
            throw new DAOException(LoggerMessage.ERROR_GET_INVOICE, e);
        }
        return Optional.empty();
    }


    @Override
    public List<Invoice> getAllByStatus(boolean paid) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM invoices WHERE paid=?")) {
            preparedStatement.setBoolean(1, paid);
            return getInvoiceList(preparedStatement.executeQuery(), null, null, paid);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.ERROR_GET_INVOICE_BY_STATUS);
            throw new DAOException(LoggerMessage.ERROR_GET_INVOICE_BY_STATUS, e);
        }
    }


    private List<Invoice> getInvoiceList(ResultSet resultSet, Integer invoiceId, Integer orderId, Boolean paid) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        while (resultSet.next()) {
            Invoice invoice = getInvoice(resultSet, invoiceId, orderId, paid);
            invoices.add(invoice);
        }
        return invoices;
    }

    private Invoice getInvoice(ResultSet resultSet, Integer invoiceId, Integer orderId, Boolean paid) throws SQLException {
        int id = (invoiceId == null) ? resultSet.getInt("id") : invoiceId;
        int order = (orderId == null) ? resultSet.getInt("orderId") : orderId;
        boolean isPaid = (paid == null) ? resultSet.getBoolean("isPaid") : paid;
        Timestamp dateCreated = resultSet.getTimestamp("dateCreated");
        double cost = resultSet.getDouble("cost");
        int discount = resultSet.getInt("discount");
        double charge = resultSet.getDouble("charge");
        return new Invoice(id, orderDAO.getById(order), dateCreated, isPaid, cost, discount, charge);
    }


}
