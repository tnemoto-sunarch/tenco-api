package jp.co.sunarch.tenco.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequestAuthInfo implements Serializable{

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("pass")
	private String pass = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
