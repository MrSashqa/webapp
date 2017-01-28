package project.model.entity;

public class User {

	private int id; 
	private String login;
	private String password;

	@Override
	public String  toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				'}';
	}

	private boolean isAdmin;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
}
