package net.javaguide.springboot.model;

import lombok.Data;
import net.javaguide.springboot.entity.User;

@Data
public class JwtAuthResponse {

	private String token;
	
	private User user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
