package com.kenesis.security.domain;

public class AuthenticationResponse {
	private String token;

	public AuthenticationResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
