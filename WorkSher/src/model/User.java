package model;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	private long userid;
	private String username;
	private String password;
	private String name;
	private String email;
	private Date dateJoined;
	private String role;

	// CONSTRUCTORS
	public User() {		
	}
	
	public User(String username, String password, String name, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.dateJoined = new Date();	// Current date by default
		this.role = "user"; 			// User by default
	}
	
	// from Quynh
	// Constructor with some arguments for testing order because we havent had getUserByID() method yet
	// Please feel free to delete it
	public User(long userid, String username, String password, String name, String email, String role) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.dateJoined = dateJoined;
		this.role = role;
	}
	
	// GETTERS
	public long getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getDateJoined() {
		return dateJoined;		
	}
	
	public String getRole() {
		return role;
	}

	// SETTERS
	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public void setRole(String role) {
		this.role = role;
	}
}