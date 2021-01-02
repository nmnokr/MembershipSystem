package DATABASE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import BEAN.DatabaseProperties;
 

public class DatabaseHelper {
	Logger logger = Logger.getLogger(DatabaseHelper.class);

	DatabaseProperties databaseProperties = null;

	protected void init(DatabaseProperties databaseProperties) {
		this.databaseProperties = databaseProperties;
	}

	public Connection getConnection() throws Exception {
		if (databaseProperties.isDataSource())
			return getRegularConnection();
		else
			return getDataSourceConnection();
	}

	public Connection getRegularConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName(databaseProperties.getDatabaseDriver()).newInstance();
			conn = (Connection) DriverManager.getConnection(databaseProperties.getDatabaseConnectionURL(),
					databaseProperties.getUsername(), databaseProperties.getPassword());
			logger.info("Baglanti Gönderildi");
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Baglantý Geri Dönmedi");
			throw exception;
		}
		return conn;
	}

	public Connection getDataSourceConnection() throws Exception {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("db32bit");
			conn = ds.getConnection();
			logger.info("Baglanti Gönderildi");

		} catch (Exception e) {
			logger.error("Baglantý Geri Dönmedi");
		}

		return conn;
	}

	public Connection getTransactionalConnection() throws Exception {
		Connection conn = getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	public void closeConnection(Connection conn) throws Exception {

		if (!conn.isClosed()) {
			try {
				conn.close();
				logger.info("Baglantý Kapatýldý");
			} catch (Exception exception) {
				exception.printStackTrace();

				logger.error("Baglantý kapatýlamadi");
			}
		}
	}
 
}
