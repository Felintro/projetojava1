package model;

public class ModelUser {
	
	private short id;
	private String name;
	private String email;
	private String password;
	private short age;
	
	/* Construtores */
	
	public ModelUser() {
		
	}
	
	public ModelUser(short id, String name, String email, String password, short age) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
	}
	
	/* Gets e Sets */
	
	public long getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	
	/* toString */

	@Override
	public String toString() {
		return "Model: [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", age=" + age
				+ "]";
	}
	
}
