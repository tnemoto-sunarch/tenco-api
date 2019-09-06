package jp.co.sunarch.tenco.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequest implements Serializable{

	@JsonProperty("user_id")
	private String userId = null;

	@JsonProperty("request_id")
	private String requestId = null;

	@JsonProperty("check_target")
	private JsonRequestCheck checkTarget = null;

	@JsonProperty("update_memo")
	private JsonRequestUpdateMemo updateMemo = null;

	@JsonProperty("auth_info")
	private JsonRequestAuthInfo authInfo = null;

	@JsonProperty("add_check_list")
	private JsonRequestAddCheckList addCheckList = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public JsonRequestCheck getCheckTarget() {
		return checkTarget;
	}

	public void setCheckTarget(JsonRequestCheck checkTarget) {
		this.checkTarget = checkTarget;
	}

	public JsonRequestUpdateMemo getUpdateMemo() {
		return updateMemo;
	}

	public void setUpdateMemo(JsonRequestUpdateMemo updateMemo) {
		this.updateMemo = updateMemo;
	}

	public JsonRequestAuthInfo getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(JsonRequestAuthInfo authInfo) {
		this.authInfo = authInfo;
	}

	public JsonRequestAddCheckList getAddCheckList() {
		return addCheckList;
	}

	public void setAddCheckList(JsonRequestAddCheckList addCheckList) {
		this.addCheckList = addCheckList;
	}

}
