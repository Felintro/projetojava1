package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.UserPosJava;

public class UserPosDAO {
	
	/* DAO = Data Access Object = Objeto de acesso aos dados */

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void sqlInsert(UserPosJava userPosJava) throws SQLException {

		/* INÍCIO BLOCO */
		/* Responsável por prepararar uma Query SQL, e injetar ela no banco através do objeto connection criado */

		try {
			
			System.out.println("Iniciando sequência de Commit!");
	
			String sql = "INSERT INTO userposjava (nome, email, login, idade) VALUES (?, ?, ?, ?);";	/* Preparar a Query SQL */
			PreparedStatement insert = connection.prepareStatement(sql);			/* Injetar a Query no objeto connection */

			insert.setString(1, userPosJava.getNome()); 	/* Recebimento dos dados */
			insert.setString(2, userPosJava.getEmail()); 	/* Recebimento dos dados */
			

			/* FIM BLOCO */

			insert.execute(); 		/* Executa a Query dentro do banco */
			/* connection.commit();	 Grava os dados da Query no banco */
			/* O comando commit é necessário, pois o comando setAutoCommit(false) foi aplicado na classe de conexão com o BD */
			
		} catch (Exception e) {
			
			e.printStackTrace();
			connection.rollback();	/* Reverte a operação feita no banco de dados caso alguma Exceção seja pega no bloco Try Catch */
			
		}
		
		connection.commit();	/* Grava os dados da Query no banco */
		System.out.println("Dados gravados com sucesso!\n\n");
		System.out.println("================================================================================\n\n");

	}
	
	public List<UserPosJava> sqlSelect (int id) throws SQLException{
		
		List<UserPosJava> lista = new ArrayList<>();	/* Lista responsável por recuperar os dados oriundos do banco */
		
		String sql = "SELECT * FROM userposjava;";	/* Query */
		
		PreparedStatement select = connection.prepareStatement(sql);	/* Objeto responsável por executar a query */
		ResultSet resultado = select.executeQuery();	/* ResultSet recebe os dados do banco (todos) */
		
		while(resultado.next()) {	/* Método next é responsável por percorrer o ResultSet e retorna um booleano a cada iteração (True se houver uma nova linha, False se não houver) */
			
			UserPosJava userPosJava = new UserPosJava();	/* Objeto modelo declarado para atribuição dos dados e alimentar a lista */
			
			userPosJava.setId(resultado.getLong("id"));			/* Setando os atributos do objeto com os dados recebidos do banco */
			userPosJava.setNome(resultado.getString("nome"));	/* Setando os atributos do objeto com os dados recebidos do banco */
			userPosJava.setEmail(resultado.getString("email"));	/* Setando os atributos do objeto com os dados recebidos do banco */
			
			lista.add(userPosJava);	/* Adicionando o objeto na lista */
		}
		
		return lista;	/* Retorno do método */ 
		
	}
	
	public UserPosJava sqlSelectByID(Long id) throws SQLException {
		
		String sql = "SELECT * FROM userposjava WHERE id = " + id;	/* Query */
		UserPosJava userPosJava = new UserPosJava();
		
		PreparedStatement select = connection.prepareStatement(sql);	/* Objeto responsável por executar a query */
		ResultSet resultado = select.executeQuery();	/* ResultSet recebe os dados do banco (todos) */
		
		while(resultado.next()) {	/* Método next é responsável por percorrer o ResultSet e retorna um booleano a cada iteração (True se houver uma nova linha, False se não houver) */
			
			userPosJava.setId(resultado.getLong("id"));			/* Setando os atributos do objeto com os dados recebidos do banco */
			userPosJava.setNome(resultado.getString("nome"));	/* Setando os atributos do objeto com os dados recebidos do banco */
			userPosJava.setEmail(resultado.getString("email"));	/* Setando os atributos do objeto com os dados recebidos do banco */
			
		}
	
		return userPosJava;	/* Retorno do método */
		
	}

}