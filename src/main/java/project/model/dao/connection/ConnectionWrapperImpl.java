package project.model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import project.util.LoggerMessage;

public class ConnectionWrapperImpl implements ConnectionWrapper {
    private static final Logger logger = Logger.getLogger(ConnectionFactory.class);
    private Connection connection;
    private boolean isTransactionStarted;
    private boolean isTransactionCommitted;

    public ConnectionWrapperImpl(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            isTransactionStarted = true;
        } catch (SQLException e) {
            logger.error(LoggerMessage.ERROR_BEGIN_TRANSACTION);
            throw new RuntimeException(LoggerMessage.ERROR_BEGIN_TRANSACTION, e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void rollBack() {
        try {
            connection.rollback();
            isTransactionStarted = false;
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error(LoggerMessage.ERROR_ROLLBACK_TRANSACTION);
            throw new RuntimeException(LoggerMessage.ERROR_ROLLBACK_TRANSACTION, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            isTransactionCommitted = true;
        } catch (SQLException e) {
            logger.error(LoggerMessage.ERROR_COMMIT_TRANSACTION);
            throw new RuntimeException(LoggerMessage.ERROR_COMMIT_TRANSACTION, e);
        }
    }

    @Override
    public void close() {
        if (isTransactionStarted && !isTransactionCommitted) {
            rollBack();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LoggerMessage.ERROR_CLOSE_CONNECTION);
            throw new RuntimeException(LoggerMessage.ERROR_CLOSE_CONNECTION, e);
        }
    }
}
