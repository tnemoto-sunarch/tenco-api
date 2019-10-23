package jp.co.sunarch.tenco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseAuthInfo {

	@JsonProperty("login")
	private boolean login = false;

	@JsonProperty("admin")
	private boolean admin = false;

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
