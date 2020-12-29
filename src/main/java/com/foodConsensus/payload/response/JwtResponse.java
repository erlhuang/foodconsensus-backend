package com.foodConsensus.payload.response;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String name;
	private boolean isAdmin;

	public JwtResponse(String accessToken, int id, String name, boolean isAdmin) {
		this.token = accessToken;
		this.id = id;
		this.name = name;
		this.isAdmin = isAdmin;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}