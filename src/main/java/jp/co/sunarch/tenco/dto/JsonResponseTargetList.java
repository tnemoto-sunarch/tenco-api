package jp.co.sunarch.tenco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseTargetList {

	@JsonProperty("id")
	private int id = -1;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("sort")
	private int sort = -1;

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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
