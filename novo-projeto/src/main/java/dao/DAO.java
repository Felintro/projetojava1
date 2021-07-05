package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constant.ConfigConstant;
import jdbcconnection.SingleConnection;
import model.ModelUser;

/*  DAO criado para a tabela userposjava, alteração feita e migração de dados da
 *  tabela userposjava para a tabela register efetuada. 
 *  
 *  Tabela configurada dinamicamente pela classe ConfigConstant.
 *  
 *  Códigos escritos em inglês para treinar a aplicação de boas práticas de código.
 *  
 *  Comentários em português para fácil entendimento.
 *  
 *  As colunas do banco de dados foram nomeadas com _ antes do nome do atributo devido os nomes em inglês
 *  coincidirem com palavras reservadas do SQL. Logo, o atributo de nome (por exemplo), virou _name.
 *  
*/

public class DAO {
	
	/* Atributos */

	private Connection connection;
	
	/* Construtor */
	
	public DAO() {
		connection = SingleConnection.getConnection();
	}
	
	/* Métodos para execução das queries SQL */
	
	public void sqlInsert(ModelUser user) throws SQLException {
		
		/* Método em uso, insert para a tabela */
			
		try {
			
			ConfigConstant config = new ConfigConstant();
			
			
			String query = "INSERT INTO ? (_name, _email, _password, _age) VALUES (?, ?, ?, ?);";
			PreparedStatement insert = connection.prepareStatement(query);

			insert.setString(1, ConfigConstant.DATABASE_TABLE);
			insert.setString(2, user.getName());
			insert.setString(3, user.getEmail());
			insert.setString(4, user.getPassword());
			insert.setShort(5, user.getAge());
			
			insert.execute();
			connection.commit();
			System.out.println("Query executada com sucesso!");
			
		}
		
		catch(Exception e) {
			
			System.out.println("Erro detectado!");
			connection.rollback();		/* Reverter a operação em caso de captura de exceção */
			System.out.println("Rollback executado com sucesso!\nQuery revertida.");
			e.printStackTrace();
				
		}
			
	}
	
	public void sqlUpdateName(ModelUser user) throws SQLException {
		
		try {
			
			String query = "UPDATE ? SET _name = ?, _email = ?, _password = ?, _age = ?  WHERE _id = ?;";
			
			PreparedStatement update = connection.prepareStatement(query);
			
			update.setString(1, ConfigConstant.DATABASE_TABLE);
			update.setString(2, user.getName());
			update.setString(3, user.getEmail());
			update.setString(4, user.getPassword());
			update.setShort(5, user.getAge());
			update.setShort(6, user.getId());
			
			update.execute();
			System.out.println("Atualização executada com sucesso!");
			
			connection.commit();
			
		} catch (SQLException e) {
			connection.rollback();
			System.out.println("Erro identificado!\n\nQuery revertida!\n\n");
			e.printStackTrace();
		}
		
	}
	
	public ModelUser sqlSelectById(short _id) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _id */
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM ? WHERE _id= ?;";
		PreparedStatement selectById = connection.prepareStatement(query);
		selectById.setString(1, ConfigConstant.DATABASE_TABLE);
		selectById.setShort(2, _id);
		ResultSet result = selectById.executeQuery();
		
		while(result.next()) {
			
			treatModelUser(result, user);
			
		}
		
		return user;
		
	}
	
	public List<ModelUser> sqlSelectByName(String _name) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _name 
		 * 
		 * Método retorna uma lista, pois podemos ter mais de 1 registro no banco, cujo
		 * nome seja igual.
		 * 
		 * */
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM ? WHERE _name = '?';";
		/* No SQL usamos aspas simples '' para trabalhar com texto, por isso tivemos que aplicá-las acima */
		
		PreparedStatement selectByName = connection.prepareStatement(query);
		selectByName.setString(1, ConfigConstant.DATABASE_TABLE);
		selectByName.setString(2, _name);
		ResultSet result = selectByName.executeQuery();
		
		
		while(result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
		
	}
	
	public ModelUser sqlSelectByEmail(String _email) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _email */
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM ? WHERE _email = '?';";
		PreparedStatement selectByEmail = connection.prepareStatement(query);
		selectByEmail.setString(1, ConfigConstant.DATABASE_TABLE);
		selectByEmail.setString(2, _email);
		ResultSet result = selectByEmail.executeQuery();
		
		treatModelUser(result, user);
		
		return user;
			
	}

	public List<ModelUser> sqlSelectByAge(short _age) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _age */
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM ? WHERE _age = ?;";
		PreparedStatement selectByAge = connection.prepareStatement(query);
		selectByAge.setString(1, ConfigConstant.DATABASE_TABLE);
		selectByAge.setShort(2, _age);
		ResultSet result = selectByAge.executeQuery();
		
		while (result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
	}
	
	public void TransferToRegister() throws SQLException { 
		
	/* Método criado para transferir os dados da tabela userposjava para a tabela register */
		
		List<ModelUser> list = sqlSelectUserposjava();
		
		for (ModelUser user : list) {
			
			sqlInsert(user);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/* Métodos obsoletos (aplicados a tabela userposjava) */
	
	public void sqlInsertIntoUserposjava(ModelUser user) throws SQLException {
		
	/* Método de insert na tabela userposjava */
		
		try {
			
			String query = "INSERT INTO userposjava (nome, email, senha, idade) VALUES (?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(query);
			
			insert.setString(1, user.getName());
			insert.setString(2, user.getEmail());
			insert.setString(3, user.getPassword());
			insert.setShort(4, user.getAge());
			
			insert.executeUpdate();
			connection.commit();
			System.out.println("Query executada com sucesso!");
			
		}
		
		catch(Exception e) {
			
			System.out.println("Erro detectado!");
			connection.rollback();		/* Reverter a operação em caso de captura de exceção */
			System.out.println("Rollback executado com sucesso!\nQuery revertida.");
			e.printStackTrace();
			
		}
		
	}
	
	public List<ModelUser> sqlSelectUserposjava() throws SQLException {
		
	/* Método de select para tabela userposjava */
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM userposjava;";
		
		PreparedStatement select = connection.prepareStatement(query);
		ResultSet result = select.executeQuery();
		
		while(result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
		
	}
	
	/* Métodos auxiliares para reaproveitamento de código */
	
	private void treatList(ResultSet result, List<ModelUser> list) throws SQLException {
		
	/* Método responsável por receber um ResultSet e uma lista de objetos ModelUser,
	*  setar os atributos do objeto e inserir na lista.
	*/
			
		ModelUser user = new ModelUser();
		
		user.setId(result.getShort("_id"));
		user.setName(result.getString("_name"));
		user.setEmail(result.getString("_email"));
		user.setPassword(result.getString("_password"));
		user.setAge(result.getShort("_age"));
		
		list.add(user);
		
	}
	
	private void treatModelUser(ResultSet result, ModelUser user) throws SQLException {
		
	/* Método responsável por receber um ResultSet e um ModelUser
	*  e setar os atributos do objeto ao receber um ResultSet vindo de uma consulta ao banco de dados
	*/
		
		user.setId(result.getShort("_id"));
		user.setName(result.getString("_name"));
		user.setEmail(result.getString("_email"));
		user.setPassword(result.getString("_password"));
		user.setAge(result.getShort("_age"));
		
	}

}