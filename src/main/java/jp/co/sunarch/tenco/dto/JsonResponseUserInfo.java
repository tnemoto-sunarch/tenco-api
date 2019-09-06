package jp.co.sunarch.tenco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseUserInfo {

	@JsonProperty("no")
	private int no = -1;

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("admin")
	private boolean admin = false;

	@JsonProperty("login")
	private boolean login = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
}
