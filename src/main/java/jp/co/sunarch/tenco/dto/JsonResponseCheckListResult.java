package jp.co.sunarch.tenco.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseCheckListResult implements Serializable{

	@JsonProperty("no")
	private int no = -1;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("status")
	private boolean status = false;

	@JsonProperty("check_time")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Tokyo")
	private Date checkTime = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
