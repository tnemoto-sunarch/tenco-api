package jp.co.sunarch.tenco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseAuthInfo {

	@JsonProperty("login")
	private boolean login = false;

	@JsonProperty("type")
	private String type = null;

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
