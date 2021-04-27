package test;


import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

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
	public void testInsertIntoRegister() throws SQLException {
		
		ModelUser user = new ModelUser();
		DAO dao = new DAO();
		
		String _name = JOptionPane.showInputDialog("Insira o nome:");
		String _email = JOptionPane.showInputDialog("Insira o email:");
		String _password = JOptionPane.showInputDialog("Insira a senha:");
		short _age = (short) Integer.parseInt(JOptionPane.showInputDialog("Insira a idade:"));
		
		user.setName(_name);
		user.setEmail(_email);
		user.setPassword(_password);
		user.setAge(_age);

		dao.sqlInsertIntoRegister(user);
		
		/* Teste executado! */
		
	}
	
	@Test
	public void testSelectAllUserposjava() {
		
		DAO dao = new DAO();
		
		try {
			
			List<ModelUser> list = dao.sqlSelectAllUserposjava();
			
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
	public void testSelectByName() {
		
		DAO dao = new DAO();
		
		String _name = "beto";
		
		try {
			
			List<ModelUser> list = dao.sqlSelectByName(_name);
			
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
	public void testTransferToRegister() throws SQLException {
		
		DAO dao = new DAO();
		
		dao.TransferToRegister();
		
	}
	
}