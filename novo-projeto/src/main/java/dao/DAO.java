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
	
	/* Construtor*/
	
	public DAO() {
		connection = SingleConnection.getConnection();
	}
	
	/* Métodos para execução das queries SQL */
	
	public void sqlInsertIntoRegister(ModelUser user) throws SQLException {
		
		/* Método em uso, insert para a tabela register */
			
		try {
			
			String query = "INSERT INTO " + ConfigConstant.DATABASE_TABLE + " (_name, _email, _password, _age) VALUES (?, ?, ?, ?)";
			PreparedStatement insertToRegister = connection.prepareStatement(query);
			
			insertToRegister.setString(1, user.getName());
			insertToRegister.setString(2, user.getEmail());
			insertToRegister.setString(3, user.getPassword());
			insertToRegister.setShort(4, user.getAge());
			
			insertToRegister.executeUpdate();
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
	
	public ModelUser sqlSelectById(short _id) throws SQLException { //Em TEste!
		
		/* Método em uso, select para a tabela register com filtro na coluna _id */
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM " + ConfigConstant.DATABASE_TABLE + " WHERE _id=" + _id + ";";
		PreparedStatement selectById = connection.prepareStatement(query);
		ResultSet result = selectById.executeQuery();
		
		while(result.next()) {
			
			treatModelUser(result, user);
			
		}
		
		return user;
		
	}
	
	public List<ModelUser> sqlSelectByName(String _name) throws SQLException {
		
		/* Método em uso, select para a tabela register com filtro na coluna _name */
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + ConfigConstant.DATABASE_TABLE + " WHERE _name='" + _name + "';";
		/* No SQL usamos aspas simples '' para trabalhar com texto, por isso tivemos que aplicá-las acima */
		
		PreparedStatement selectByNome = connection.prepareStatement(query);
		ResultSet result = selectByNome.executeQuery();
		
		while(result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
		
	}
	
	public ModelUser sqlSelectByEmail(String _email) throws SQLException {
		
		/* Método em uso, select para a tabela register com filtro na coluna _email */
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM " + ConfigConstant.DATABASE_TABLE + " WHERE _email='" + _email + "';";
		PreparedStatement selectByEmail = connection.prepareStatement(query);
		ResultSet result = selectByEmail.executeQuery();
		
		treatModelUser(result, user);
		
		return user;
			
	}

	public List<ModelUser> sqlSelectByAge(short _age) throws SQLException {
		
		/* Método em uso, select para a tabela register com filtro na coluna _age */
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + ConfigConstant.DATABASE_TABLE + " WHERE _age=" + _age + ";";
		PreparedStatement selectByAge = connection.prepareStatement(query);
		ResultSet result = selectByAge.executeQuery();
		
		while (result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
	}
	
	public void TransferToRegister() throws SQLException { 
		
	/* Método criado para transferir os dados da tabela userposjava para a tabela register */
		
		List<ModelUser> list = sqlSelectAllUserposjava();
		
		for (ModelUser user : list) {
			
			sqlInsertIntoRegister(user);
			
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
	
	public List<ModelUser> sqlSelectAllUserposjava() throws SQLException {
		
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
	
	/* Métodos auxiliares */
	
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
	*  e setar os atributos do objeto.
	*/
		
		user.setId(result.getShort("_id"));
		user.setName(result.getString("_name"));
		user.setEmail(result.getString("_email"));
		user.setPassword(result.getString("_password"));
		user.setAge(result.getShort("_age"));
		
	}

}