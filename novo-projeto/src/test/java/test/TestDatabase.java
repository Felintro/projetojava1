package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import dao.DAO;
import jdbcconnection.SingleConnection;
import model.ModelPhone;
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

		String _name = JOptionPane.showInputDialog(null, "Insira o NOME:", "testSqlInsert", 1);
		String _email = JOptionPane.showInputDialog(null, "Insira o E-MAIL:", "testSqlInsert", 1);
		String _password = JOptionPane.showInputDialog(null, "Insira a SENHA:", "testSqlInsert", 1);
		short _age = Short.parseShort(JOptionPane.showInputDialog(null, "Insira a IDADE:", "testSqlInsert", 1));

		user.setName(_name);
		user.setEmail(_email);
		user.setPassword(_password);
		user.setAge(_age);

		dao.sqlInsertRegister(user);

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

		short id = Short
				.parseShort(JOptionPane.showInputDialog(null, "Insira o ID para consulta:", "testSelectById", 1));

		try {
			user = dao.sqlSelectByIdRegister(id);
			System.out.println(user);

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Erro!");

		}

	}

	@Test
	public void testSelectByName() {

		DAO dao = new DAO();
		List<ModelUser> list = new ArrayList<>();

		String _name = JOptionPane.showInputDialog(null, "Insira o NOME para consulta:", "testSelectByName", 1);

		try {

			list = dao.sqlSelectByNameRegister(_name);

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

		short age = Short
				.parseShort(JOptionPane.showInputDialog(null, "Insira a IDADE para consulta:", "testSelectByAGE", 1));

		try {

			List<ModelUser> list = dao.sqlSelectByAgeRegister(age);

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

		String email = JOptionPane.showInputDialog(null, "Insira o E-MAIL para consulta:", "testSelectByEmail", 1);

		try {
			ModelUser user = dao.sqlSelectByEmailRegister(email);
			System.out.println(user);

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Erro!");

		}

	}

	@Test
	public void testUpdate() throws SQLException {

		DAO dao = new DAO();

		short id = Short.parseShort(JOptionPane.showInputDialog(null, "Insira o ID para atualizar:", "testUpdate", 1));

		int choose = Integer.parseInt(
				JOptionPane.showInputDialog("Selecione o campo para atualização:\n1.Nome\n2.E-mail\n3.Senha\n4.Idade"));

		ModelUser user = dao.sqlSelectByIdRegister(id);

		System.out.println("Registro antigo:");
		System.out.println(user);

		JOptionPane.showMessageDialog(null, "Observe o console!");

		switch (choose) {

		case 1:
			String name = JOptionPane.showInputDialog(null, "Insira o NOME para atualizar:", "testUpdate", 1);
			user.setName(name);
			break;

		case 2:
			String email = JOptionPane.showInputDialog(null, "Insira o E-MAIL para atualizar:", "testUpdate", 1);
			user.setEmail(email);
			break;

		case 3:
			String password = JOptionPane.showInputDialog(null, "Insira a SENHA para atualizar:", "testUpdate", 1);
			user.setPassword(password);
			break;

		case 4:
			short age = Short
					.parseShort(JOptionPane.showInputDialog(null, "Insira o IDADE para atualizar:", "testUpdate", 1));
			user.setAge(age);
			break;

		}

		System.out.println("Registro novo:");
		System.out.println(user);

		JOptionPane.showMessageDialog(null, "Observe o console!");

		dao.sqlUpdateRegister(user);

	}

	@Test
	public void testTransferToRegister() throws SQLException {

		DAO dao = new DAO();

		dao.TransferToRegister();

	}

	@Test
	public void testUpdatePhone() throws SQLException {

		DAO dao = new DAO();

		short _id = Short.parseShort(JOptionPane.showInputDialog("Insira o ID do telefone para atualizar!") );
		
		ModelPhone phone = dao.sqlSelectByIdPhone(_id);
		
		int choose = Integer.parseInt(JOptionPane.showInputDialog("Selecione o campo para atualizar:\n1.Número do telefone\n2.Tipo\n3.ID do usuário"));
		
		switch (choose) {
		
			case 1: 
				String _number = JOptionPane.showInputDialog("Insira o número para atualizar:");
				phone.set_number(_number);
				break;
				
			case 2: 
				String _type = JOptionPane.showInputDialog("Insira o tipo para atualizar:");
				phone.set_type(_type);
				break;
	
			case 3: 
				short _user_id = Short.parseShort(JOptionPane.showInputDialog("Insira o id do usuario para atualizar:"));
				phone.set_user_id(_user_id);
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Erro!\nDigite o valor correto!");
				break;
		}
		
		dao.sqlUpdatePhone(phone);
		JOptionPane.showMessageDialog(null, "Observe o console!");

	}

}