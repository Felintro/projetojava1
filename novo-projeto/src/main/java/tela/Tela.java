package tela;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Tela {
	
	JTextField nome;
	JTextField email;
	JTextField senha;
	JTextField idade;
	JButton novoCadastro;
	JButton consulta;
	JButton teste;
	
	public Tela() {
		
		JFrame jTela = new JFrame("Teste banco de dados");
		jTela.setSize(300, 450);
		jTela.setLocationRelativeTo(null);
		
		jTela.setLayout(new FlowLayout(1, 5, 5));
		
		novoCadastro = new JButton("Cadastrar");
		consulta = new JButton    ("Consultar");
		teste = new JButton       ("   Testar");
		
		jTela.add(novoCadastro);
		jTela.add(consulta);
		jTela.add(teste);
		
		jTela.setVisible(true);
		jTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
	}
	
	

}
