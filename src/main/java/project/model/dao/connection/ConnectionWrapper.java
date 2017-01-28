package project.model.dao.connection;

import java.sql.Connection;

public interface ConnectionWrapper extends AutoCloseable {

	void beginTransaction() ;

	Connection getConnection() ;

	void rollBack() ;

	void commit() ;

	@Override
	void close() ;

}
