package test;


import java.sql.SQLException;
import java.util.ArrayList;
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
	public void testSqlInsert() throws SQLException {
		
		ModelUser user = new ModelUser();
		DAO dao = new DAO();
		
		
		String _name = JOptionPane.showInputDialog(null,"Insira o NOME:", "testSqlInsert", 1);
		String _email = JOptionPane.showInputDialog(null,"Insira o E-MAIL:", "testSqlInsert", 1);
		String _password = JOptionPane.showInputDialog(null,"Insira a SENHA:", "testSqlInsert", 1);
		short _age = Short.parseShort(JOptionPane.showInputDialog(null,"Insira a IDADE:", "testSqlInsert", 1));
		
		user.setName(_name);
		user.setEmail(_email);
		user.setPassword(_password);
		user.setAge(_age);

		dao.sqlInsert(user);
		
		/* Teste executado! */
		
	}
	
	@Test
	public void testSelectUserposjava() {
		
		DAO dao = new DAO();
		
		try {
			
			List<ModelUser> list = dao.sqlSelectUserposjava();
			
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
		
		short id = Short.parseShort(JOptionPane.showInputDialog(null,"Insira o ID para consulta:", "testSelectById", 1));
		
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
		
		String _name = JOptionPane.showInputDialog(null,"Insira o NOME para consulta:", "testSelectByName", 1);
		
		try {
			
			List<ModelUser> list = dao.sqlSelectByName(_name);
			
			for (ModelUser model : list) {
				
				System.out.println(model);
				
				
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Erro!");
		
		}
		
	}
	
	@Test
	public void testSelectByAge() {
		
		DAO dao = new DAO();
		
		short age = Short.parseShort(JOptionPane.showInputDialog(null,"Insira a IDADE para consulta:", "testSelectByAGE", 1));
		
		try {
			
			List<ModelUser> list = dao.sqlSelectByAge(age);
			
			for (ModelUser modelUser : list) {
				System.out.println(modelUser);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Erro!");
		}
		
	}
	
	@Test
	public void testSelectByEmail() {
		
		DAO dao = new DAO();
		
		
		String email = JOptionPane.showInputDialog(null,"Insira o E-MAIL para consulta:", "testSelectByEmail", 1);
		
		try {
			ModelUser user = dao.sqlSelectByEmail(email);
			System.out.println(user);
				
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Erro!");
			
		}	
		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		
		DAO dao = new DAO();
		
		short id = Short.parseShort(JOptionPane.showInputDialog(null,"Insira o ID para atualizar:", "testUpdate", 1));
		
		int choose = Integer.parseInt(JOptionPane.showInputDialog("Selecione o campo para atualização:\n1.Nome\n2.E-mail\n3.Senha\n4.Idade"));
		
		ModelUser user = dao.sqlSelectById(id);
		
		System.out.println("Registro antigo:");
		System.out.println(user);

		JOptionPane.showMessageDialog(null, "Observe o console!");
		
		switch(choose) {
		
			case 1:
				String name = JOptionPane.showInputDialog(null,"Insira o NOME para atualizar:", "testUpdate", 1);
				user.setName(name);
				break;
			
			case 2: 
				String email = JOptionPane.showInputDialog(null,"Insira o E-MAIL para atualizar:", "testUpdate", 1);
				user.setEmail(email);
				break;
		
			case 3:
				String password = JOptionPane.showInputDialog(null,"Insira a SENHA para atualizar:", "testUpdate", 1);
				user.setPassword(password);
				break;
				
			case 4: 
				short age = Short.parseShort(JOptionPane.showInputDialog(null,"Insira o IDADE para atualizar:", "testUpdate", 1));
				user.setAge(age);
				break;
		
		}
		
		System.out.println("Registro novo:");
		System.out.println(user);
		
		JOptionPane.showMessageDialog(null, "Observe o console!");

		dao.sqlUpdate(user);
		
	}
	
	@Test
	public void testTransferToRegister() throws SQLException {
		
		DAO dao = new DAO();
		
		dao.TransferToRegister();
		
	}
	
}