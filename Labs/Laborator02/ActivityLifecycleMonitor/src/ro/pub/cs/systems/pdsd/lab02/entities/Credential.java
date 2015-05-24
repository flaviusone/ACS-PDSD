package ro.pub.cs.systems.pdsd.lab02.entities;

public class Credential {
	
	private String username;
	private String password;
	
	public Credential() {
		username = new String();
		password = new String();
	}
	
	public Credential(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

}
