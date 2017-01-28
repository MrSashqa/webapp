package project.model.dao.connection;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import project.util.LoggerMessage;
//TODO : select select vs join and podzapros (select select)
public class ConnectionFactory {

    private static final Logger logger = Logger.getLogger(ConnectionFactory.class);
    private static final ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    private DataSource dataSource;

    private ConnectionFactory() {
        try {
            Context ctx = (Context) (new InitialContext()).lookup("java:comp/env");
            dataSource = (DataSource) ctx.lookup("jdbc/restaurant");
            logger.info(LoggerMessage.SUCCESS_CONNECTION_FACTORY_INIT);
        } catch (NamingException e) {
            logger.error(LoggerMessage.ERROR_CONNECTION_FACTORY_INIT);
            throw new RuntimeException(LoggerMessage.ERROR_CONNECTION_FACTORY_INIT, e);
        }
    }

    public ConnectionWrapper getConnection() {
        try {
            logger.info(LoggerMessage.SUCCESS_CONNECTION);
            return new ConnectionWrapperImpl(dataSource.getConnection());
        } catch (SQLException e) {
            logger.error(LoggerMessage.ERROR_CONNECTION);
            throw new RuntimeException(LoggerMessage.ERROR_CONNECTION, e);
        }
    }
}
