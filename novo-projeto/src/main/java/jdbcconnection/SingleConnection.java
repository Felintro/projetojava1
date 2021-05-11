package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import constant.ConfigConstant;

public class SingleConnection {
	
	/* Classe criada para conectar a aplicação ao banco de dados
	*  
	*  Utilizado o padrão Singleton
	* 
	*/
	
	/* Atributos */
	
	private static String url = ConfigConstant.DATABASE_URL; 			/* URL do banco de dados */
	private static String user = ConfigConstant.DATABASE_USER;			/* Usuário do banco de dados */
	private static String password = ConfigConstant.DATABASE_PASSWORD;	/* Senha do banco de dados */
	private static String driver = ConfigConstant.DATABASE_DRIVER;		/* Driver do banco de dados */
	private static Connection connection = null;
	
	static { 
		connect();
	}
	
	/* Construtor */
	
	public SingleConnection() {
		connect();
	}
	
	/* Métodos */
	
	private static void connect() {
		
		try {
			
			if(connection == null) {
				
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conexão com o banco de dados efetuada com sucesso!\n");
				
			}

		} 
		
		catch (Exception e) {
			
			System.out.println("Erro ao efetuar a conexão com o banco de dados!");
			e.printStackTrace();
			
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}