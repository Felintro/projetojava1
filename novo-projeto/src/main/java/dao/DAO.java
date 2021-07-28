package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcconnection.SingleConnection;
import model.ModelPhone;
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
	
	/* ============================================================================
	 * =========================INÍCIO DA TABELA REGISTER==========================
	 * ============================================================================ */
	
	public void sqlInsertRegister(ModelUser user) throws SQLException {
			
		try {
			
			String query = "INSERT INTO register (_name, _email, _age, _password) VALUES (?, ?, ?, ?);";
			PreparedStatement insert = connection.prepareStatement(query);

			insert.setString(1, user.getName());
			insert.setString(2, user.getEmail());
			insert.setShort(3, user.getAge());
			insert.setString(4, user.getPassword());
			
			insert.execute();
			connection.commit();
			System.out.println("Query executada com sucesso!");
			
		}
		
		catch(Exception e) {
			
			connection.rollback();
			System.out.println("Erro identificado!\n\nQuery revertida!\n\n");
			e.printStackTrace();
				
		}
			
	}
	
	public void sqlUpdateRegister(ModelUser user) throws SQLException {

		try {
			
			String query = "UPDATE register SET _name = ?, _email = ?, _password = ?, _age = ?  WHERE _id = ?;";
			
			PreparedStatement update = connection.prepareStatement(query);
			
			update.setString(1, user.getName());
			update.setString(2, user.getEmail());
			update.setString(3, user.getPassword());
			update.setShort(4, user.getAge());
			update.setShort(5, user.getId());
			
			update.execute();
			System.out.println("Atualização executada com sucesso!");
			
			connection.commit();
			
		} catch (SQLException e) {
			connection.rollback();
			System.out.println("Erro identificado!\n\nQuery revertida!\n\n");
			e.printStackTrace();
		}
		
	}
	
	public ModelUser sqlSelectByIdRegister(short _id) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _id */
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM register WHERE _id= ?;";
		PreparedStatement selectById = connection.prepareStatement(query);
		selectById.setShort(1, _id);
		ResultSet result = selectById.executeQuery();
		
		if(result.next()) {
			
			treatModelUser(result, user);
			
		}
		
		return user;
		
	}
	
	public List<ModelUser> sqlSelectByNameRegister(String _name) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _name 
		 * 
		 * Método retorna uma lista, pois podemos ter mais de 1 registro no banco, cujo
		 * nome seja igual.
		 * 
		 * */
		
		List<ModelUser> list = new ArrayList<ModelUser>();
		
		String query = "SELECT * FROM register WHERE _name = ?;";
		/* No SQL usamos aspas simples '' para trabalhar com texto, por isso tivemos que aplicá-las acima */
		
		PreparedStatement selectByName = connection.prepareStatement(query);
		selectByName.setString(1, _name);
		ResultSet result = selectByName.executeQuery();

		while(result.next()) {

			treatList(result, list);

		}

		return list;

	}
	
	public ModelUser sqlSelectByEmailRegister(String _email) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _email */
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM register WHERE _email = ?;";
		PreparedStatement selectByEmail = connection.prepareStatement(query);
		selectByEmail.setString(1, _email);
		ResultSet result = selectByEmail.executeQuery();
		
		treatModelUser(result, user);
		
		return user;
			
	}

	public List<ModelUser> sqlSelectByAgeRegister(short _age) throws SQLException {
		
		/* Método em uso, select com filtro na coluna _age */
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM register WHERE _age = ?;";
		PreparedStatement selectByAge = connection.prepareStatement(query);
		selectByAge.setShort(1, _age);
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
			
			sqlInsertRegister(user);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/* ============================================================================
	 * ==========================FIM DA TABELA REGISTER============================
	 * ============================================================================ */
	
	/* ============================================================================
	 * ==========================INÍCIO DA TABELA PHONE============================
	 * ============================================================================ */
	
	public void sqlInsertPhone(ModelPhone modelPhone) throws SQLException {
		
		try {
			
			String query = "INSERT INTO phone(_number, _type, _user_id) VALUES (?, ?, ?);";
			PreparedStatement insert = connection.prepareStatement(query);
			
			insert.setString(1, modelPhone.get_number());
			insert.setString(2, modelPhone.get_type());
			insert.setShort(3, modelPhone.get_user_id());
			
			insert.execute();
			connection.commit();
			
			System.out.println("Query executada com sucesso!");
			
		} catch (Exception e) {
			
			System.out.println("Erro detectado!");
			connection.rollback();		/* Reverter a operação em caso de captura de exceção */
			System.out.println("Rollback executado com sucesso!\nQuery revertida.");
			e.printStackTrace();
			
		}
		
	}
	
	public void sqlUpdatePhone (ModelPhone modelPhone) throws SQLException {
		
		try {
			
			String query = "UPDATE phone SET _number=?, _type=?, _user_id=? WHERE _id=?;";
			PreparedStatement update = connection.prepareStatement(query);
			
			update.setString(1, modelPhone.get_number());
			update.setString(2, modelPhone.get_type());
			update.setShort(3, modelPhone.get_user_id());
			update.setShort(4, modelPhone.get_id());
			
			update.execute();
			connection.commit();
			
			System.out.println("Atualização efetuada com sucesso!");
			
		} catch (Exception e) {
			
			connection.rollback();
			System.out.println("Erro identificado!\n\nQuery revertida!\n\n");
			e.printStackTrace();
			
		}
		
	}
	
	public ModelPhone sqlSelectByIdPhone (short _id) throws SQLException {
		
		ModelPhone phone = new ModelPhone();
		String query = "SELECT * FROM phone WHERE _id=?;";
		PreparedStatement selectById = connection.prepareStatement(query);
		
		selectById.setShort(1, _id);
		ResultSet result = selectById.executeQuery();
		
		while (result.next()) {
			
			treatModelPhone(result, phone);
			
		}
		
		return phone;
		
	}
	
	/* ============================================================================
	 * ===========================FIM DA TABELA PHONE==============================
	 * ============================================================================ */
	
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
	*  setar os atributos do objeto e inserir o mesmo na lista.
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
		
	/* Método responsável por receber um ResultSet e um ModelUser e setar os atributos
	*  do objeto ao receber um ResultSet vindo de uma consulta ao banco de dados.
	*/
		
		user.setId(result.getShort("_id"));
		user.setName(result.getString("_name"));
		user.setEmail(result.getString("_email"));
		user.setPassword(result.getString("_password"));
		user.setAge(result.getShort("_age"));
		
	}
	
	private void treatModelPhone (ResultSet result, ModelPhone phone) throws SQLException {
		
		/* 
		*  Método responsável por receber um ResultSet e um ModelPhone e setar os atributos
		*  do objeto ao receber um ResultSet selecionado de uma consulta ao banco de dados.
		*/
		
		phone.set_id(result.getShort("_id"));
		phone.set_number(result.getString("_number"));
		phone.set_type(result.getString("_type"));
		phone.set_user_id(result.getShort("_user_id"));
		
	}

}