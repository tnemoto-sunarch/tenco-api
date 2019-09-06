package jp.co.sunarch.tenco.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequestUpdateMemo implements Serializable {

	@JsonProperty("memo")
	private String memo = null;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
