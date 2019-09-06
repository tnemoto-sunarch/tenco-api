package jp.co.sunarch.tenco.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponseCheckListDetail implements Serializable{

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("status")
	private String status = null;

	@JsonProperty("type")
	private String type = null;

	@JsonProperty("cur_count")
	private int curCount = -1;

	@JsonProperty("tot_count")
	private int totalCount = -1;

	@JsonProperty("open_time")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Tokyo")
	private Date openTime = null;

	@JsonProperty("close_time")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "Asia/Tokyo")
	private Date closeTime = null;

	@JsonProperty("memo")
	private String memo = null;

	@JsonProperty("result_list")
	private List<JsonResponseCheckListResult> resultList = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<JsonResponseCheckListResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<JsonResponseCheckListResult> resultList) {
		this.resultList = resultList;
	}

	public int getCurCount() {
		return curCount;
	}

	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
