package com.todolist.challenge.message.response;

import java.util.List;

public class JwtResponse {
	private String accessToken;
	private String id;
	private String userName;
	private List<String> authorities;


	public JwtResponse(String accessToken, String id, String userName, List<String> authorities) {
		this.accessToken = accessToken;
		this.id = id;
		this.userName = userName;
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return accessToken;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}