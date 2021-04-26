package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	private static String driver = "org.postgresql.Driver";
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conexão com o banco de dados efetuada com sucesso!\n");
				
			}

		} catch (Exception e) {
			System.out.println("Erro ao efetuar a conexão com o banco de dados!");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}