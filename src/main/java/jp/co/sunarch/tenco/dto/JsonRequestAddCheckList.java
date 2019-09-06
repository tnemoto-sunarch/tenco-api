package jp.co.sunarch.tenco.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequestAddCheckList implements Serializable{

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("type")
	private String type = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
