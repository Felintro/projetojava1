package connection;

import java.sql.Connection;
import java.sql.DriverManager;

import constant.ConfigConstant;

public class SingleConnection {
	
	private static String url = ConfigConstant.URL_DB;
	private static String user = ConfigConstant.USER_DB;
	private static String password = ConfigConstant.PASSWORD_DB;
	private static Connection connection = null;
	
	static {
		connect();
	}
	
	public SingleConnection() {
		connect();
	}
	
	private static void connect() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");		/* Carrega o driver de conexão do banco de dados */
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);			/*  */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Connection getConnection() {
		return connection;
	}

}