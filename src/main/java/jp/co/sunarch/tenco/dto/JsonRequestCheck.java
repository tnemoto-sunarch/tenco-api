package jp.co.sunarch.tenco.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequestCheck implements Serializable{

	@JsonProperty("no")
	private int no = -1;

	@JsonProperty("status")
	private boolean status = false;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
