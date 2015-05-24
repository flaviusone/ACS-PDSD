package ro.pub.cs.systems.pdsd.lab08.googlecloudmessaging.model;

public class RegisteredDevice {
	
	private int id;
	private String registrationId;
	private String username;
	private String email;
	private String timestamp;
	
	public RegisteredDevice() {
		this.id = -1;
		this.registrationId = new String();
		this.username = new String();
		this.email = new String();
		this.timestamp = new String();
	}
	
	public RegisteredDevice(
			int id,
			String registrationId,
			String username,
			String email,
			String timestamp) {
		this.id = id;
		this.registrationId = registrationId;
		this.username = username;
		this.email = email;
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRegistrationId() {
		return registrationId;
	}
	
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
