package model;

public class ModelUser {
	
/*  Classe modelo para processamento do banco de dados. 
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
	
	/* Atributos */
	
	private short _id;
	private String _name;
	private String _email;
	private String _password;
	private short _age;
	
	/* Construtores */
	
	public ModelUser() {
		
	}
	
	public ModelUser(short _id, String _name, String _email, String _password, short _age) {
		this._id = _id;
		this._name = _name;
		this._email = _email;
		this._password = _password;
		this._age = _age;
	}
	
	/* Gets e Sets */
	
	public long getId() {
		return _id;
	}

	public void setId(short _id) {
		this._id = _id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String _name) {
		this._name = _name;
	}
	public String getEmail() {
		return _email;
	}
	public void setEmail(String _email) {
		this._email = _email;
	}
	public String getPassword() {
		return _password;
	}
	public void setPassword(String _password) {
		this._password = _password;
	}
	public short getAge() {
		return _age;
	}
	public void setAge(short _age) {
		this._age = _age;
	}

	/* Método toString */
	
	@Override
	public String toString() {
		return "ModelUser [_id=" + _id + ", _name=" + _name + ", _email=" + _email + ", _password=" + _password
				+ ", _age=" + _age + "]";
	}
	
}
