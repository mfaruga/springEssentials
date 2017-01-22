package org.mfaruga.MFSpringData;

public class User {
	private Long id;
	private String login;

	public User() {
	}

	public User(Long id, String login) {
		this.id = id;
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {		
		return "ID: " + id + " login: " + login; 
	}
}
