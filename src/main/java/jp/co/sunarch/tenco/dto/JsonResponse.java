package jp.co.sunarch.tenco.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponse implements Serializable{

	@JsonProperty("result_code")
	private int resultCode = -1;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("proccess")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+9:00", timezone = "Asia/Tokyo")
	private Date proccess = null;

	@JsonProperty("check_list")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<JsonResponseCheckList> checkList = null;

	@JsonProperty("check_list_detail")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private JsonResponseCheckListDetail checkListDetail = null;

	@JsonProperty("user_info")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private JsonResponseUserInfo userInfo = null;

	@JsonProperty("auth_info")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private JsonResponseAuthInfo authInfo = null;

	@JsonProperty("target_list")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<JsonResponseTargetList> targetList = null;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getProccess() {
		return proccess;
	}

	public void setProccess(Date proccess) {
		this.proccess = proccess;
	}

	public List<JsonResponseCheckList> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<JsonResponseCheckList> checkList) {
		this.checkList = checkList;
	}

	public JsonResponseCheckListDetail getCheckListDetail() {
		return checkListDetail;
	}

	public void setCheckListDetail(JsonResponseCheckListDetail checkListDetail) {
		this.checkListDetail = checkListDetail;
	}

	public JsonResponseUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(JsonResponseUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public JsonResponseAuthInfo getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(JsonResponseAuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	public List<JsonResponseTargetList> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<JsonResponseTargetList> targetList) {
		this.targetList = targetList;
	}

}
