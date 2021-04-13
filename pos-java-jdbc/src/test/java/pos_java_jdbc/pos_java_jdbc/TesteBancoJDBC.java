package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.UserPosJava;

public class TesteBancoJDBC {
	
	@Test
	public void testeConexaoBanco() throws SQLException {
		
		SingleConnection.getConnection();

	}

	@Test
	public void initBanco() throws SQLException {
		
		Thread thread = new Thread(threadRun);
		thread.start();

	}
	
	@Test
	public void initListar() throws SQLException {
		
		UserPosDAO dao = new UserPosDAO();
		int id = Integer.valueOf(JOptionPane.showInputDialog("Insira o ID:"));
		
		List<UserPosJava> lista = dao.sqlSelect(id);
		for (UserPosJava userPosJava : lista) {
			
			System.out.println(userPosJava);
			System.out.println("=============================================\n");
			
		}
		
	}
	
	@Test
	public void initBuscar() throws SQLException {
		
		UserPosDAO dao = new UserPosDAO();
		UserPosJava userPosJava = dao.sqlSelectByID(3L);
		
		System.out.println(userPosJava);
		
	}
	
	private static Runnable threadRun = new Runnable() {
		
		@Override
		public void run() {
			
			UserPosDAO userPosDAO = new UserPosDAO();
			UserPosJava userPosJava = new UserPosJava();
			int qtde = Integer.valueOf(JOptionPane.showInputDialog("Quantos ?"));
			
			String nome = "Allan Novo Teste";
			String email = "allannovoteste@gmail.com";
			
			for(int i=1; i<=qtde; i++) {
				
				nome = "Allan Novo Teste " + i;
				email = "allanteste" + i + "@gmail.com";
				userPosJava.setNome(nome);
				userPosJava.setEmail(email);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				try {
					userPosDAO.sqlInsert(userPosJava);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	};

}