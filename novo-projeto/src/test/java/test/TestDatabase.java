package test;


import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.DAO;
import jdbcconnection.SingleConnection;
import model.ModelUser;

public class TestDatabase {
	
	@Test
	public void testDatabase() throws SQLException {
		
		SingleConnection.getConnection();
		
	}
	
	@Test
	public void testInsert() throws SQLException {
		
		ModelUser user = new ModelUser();
		DAO dao = new DAO();
		
		user.setEmail("betou2213@gmail.com");
		user.setName("BEto");
		user.setAge((short) 32);
		user.setPassword("12456");

		dao.sqlInsert(user);
		
	}
	
	@Test
	public void testSelect() {
		
		DAO dao = new DAO();
		
		try {
			
			List<ModelUser> list = dao.sqlSelectAll();
			
			for (ModelUser model : list) {
				System.out.println(model);
				System.out.println("---------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSelectById() {
		
		DAO dao = new DAO();
		ModelUser user = new ModelUser();
		
		short id = 12;
		
		try {
			user = dao.sqlSelectById(id);
			System.out.println(user);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Erro!");
			
		}	
		
	}
	
	@Test
	public void testSelectByNome() {
		
		DAO dao = new DAO();
		
		String nome = "beto";
		
		try {
			
			List<ModelUser> list = dao.sqlSelectByNome(nome);
			
			for (ModelUser model : list) {
				
				System.out.println(model);
				System.out.println("-----------------------------");
				
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Erro!");
		
		}
		
	}
	
	@Test
	public void testInsertToRegister() throws SQLException {
		
		DAO dao = new DAO();
		
		dao.InsertToRegister();
		
	}
	
}