package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcconnection.SingleConnection;
import model.ModelUser;

public class DAO {
	
	/* Atributos */

	private Connection connection;
	
	/* Construtor*/
	
	public DAO() {
		connection = SingleConnection.getConnection();
	}
	
	/* Métodos */
	
	private void treatList (ResultSet result, List<ModelUser> list) throws SQLException {
			
			ModelUser user = new ModelUser();
			
			user.setId(result.getShort("id"));
			user.setName(result.getString("nome"));
			user.setEmail(result.getString("email"));
			user.setPassword(result.getString("senha"));
			user.setAge(result.getShort("idade"));
			
			list.add(user);
		
	}
	
	public void sqlInsert (ModelUser user) throws SQLException {
		
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
		
		catch (Exception e) {
			
			System.out.println("Erro detectado!");
			connection.rollback();		/* Reverter a operação em caso de captura de exceção */
			System.out.println("Rollback executado com sucesso!\nQuery revertida.");
			e.printStackTrace();
				
		}
		
	}
	
	
	public List<ModelUser> sqlSelectAll() throws SQLException {
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM userposjava;";
		
		PreparedStatement select = connection.prepareStatement(query);
		ResultSet result = select.executeQuery();
		
		while(result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
		
	}
	
	
	
	public ModelUser sqlSelectById(short id) throws SQLException {
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM userposjava WHERE id=" + id + ";";
		PreparedStatement selectById = connection.prepareStatement(query);
		ResultSet result = selectById.executeQuery();
		
		while(result.next()) {
			
			user.setId(result.getShort("id"));
			user.setName(result.getString("nome"));
			user.setEmail(result.getString("email"));
			user.setPassword(result.getString("senha"));
			user.setAge(result.getShort("idade"));
			
		}
		
		return user;
		
	}
	
	public List<ModelUser> sqlSelectByNome(String nome) throws SQLException {
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM userposjava WHERE nome='" + nome + "';";
		/* No SQL usamos aspas simples '' para trabalhar com texto, por isso tivemos que aplicá-las acima */
		
		PreparedStatement selectByNome = connection.prepareStatement(query);
		ResultSet result = selectByNome.executeQuery();
		
		while(result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
		
	}

	public List<ModelUser> sqlSelectByIdade(short idade) throws SQLException {
		
		List<ModelUser> list = new ArrayList<>();
		
		String query = "SELECT * FROM userposjava WHERE idade=" + idade + ";";
		PreparedStatement selectByIdade = connection.prepareStatement(query);
		ResultSet result = selectByIdade.executeQuery();
		
		while (result.next()) {
			
			treatList(result, list);
			
		}
		
		return list;
	}
	
	/* public ModelUser sqlSelectByEmail(String email) throws SQLException {
		
		ModelUser user = new ModelUser();
		
		String query = "SELECT * FROM userposjava WHERE email='" + email + "';";
		PreparedStatement selectByEmail = connection.prepareStatement(query);
		ResultSet result = selectByEmail.executeQuery();
		
		user.setId(result.getShort("id"));
		user.setName(result.getString("nome"));
		
		return user;
		
	} */
	
	public void sqlInsert2 (ModelUser user) throws SQLException {
		
		try {
			
			String query = "INSERT INTO register (_name, _email, _password, _age) VALUES (?, ?, ?, ?)";
			PreparedStatement insertToRegister = connection.prepareStatement(query);
			
			insertToRegister.setString(1, user.getName());
			insertToRegister.setString(2, user.getEmail());
			insertToRegister.setString(3, user.getPassword());
			insertToRegister.setShort(4, user.getAge());
			
			insertToRegister.executeUpdate();
			connection.commit();
			System.out.println("Query executada com sucesso!");
			
		}
		
		catch (Exception e) {
			
			System.out.println("Erro detectado!");
			connection.rollback();		/* Reverter a operação em caso de captura de exceção */
			System.out.println("Rollback executado com sucesso!\nQuery revertida.");
			e.printStackTrace();
				
		}
		
	}
	
	public void sqlInsertToRegister() throws SQLException { 
		
		/* Método criado para transferir os dados da tabela userposjava para a tabela register */
		
		List<ModelUser> list = sqlSelectAll();
		
		for (ModelUser user : list) {
			
			sqlInsert2(user);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}