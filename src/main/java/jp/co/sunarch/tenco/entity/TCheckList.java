package jp.co.sunarch.tenco.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@IdClass(TCheckListPrimaryKey.class)
@Table(name = "t_checklist")
public class TCheckList extends AbstractEntity{

	@Id
	@Column(name="check_list_no", nullable = false)
	private int checkListNo = -1;

	@Column(name="check_list_id", nullable = false)
	@Size(max = 255)
	private String checkListId = null;

	@Column(name="check_list_name", nullable = false)
	@Size(max = 255)
	private String checkListName = null;

	@Column(name="check_list_type", nullable = false)
	@Size(max = 2)
	private String checkListType = null;

	@Column(name="check_list_default_type", nullable = false)
	@Size(max = 2)
	private String checkListDefaultType = null;

	@Column(name="open_time", nullable = true)
	private Date openTime = null;

	@Column(name="close_time", nullable = true)
	private Date closeTime = null;

	@Column(name="memo", nullable = true)
	@Size(max = 2000)
	private String memo = null;

	/**
	 * 00:有効
	 * 10:実施中
	 * 20:終了
	 */
	@Column(name="status", nullable = false)
	@Size(max = 2)
	private String status = null;

	public int getCheckListNo() {
		return checkListNo;
	}

	public void setCheckListNo(int checkListNo) {
		this.checkListNo = checkListNo;
	}

	public String getCheckListId() {
		return checkListId;
	}

	public void setCheckListId(String checkListId) {
		this.checkListId = checkListId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckListName() {
		return checkListName;
	}

	public void setCheckListName(String checkListName) {
		this.checkListName = checkListName;
	}

	public String getCheckListType() {
		return checkListType;
	}

	public void setCheckListType(String checkListType) {
		this.checkListType = checkListType;
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

	public String getCheckListDefaultType() {
		return checkListDefaultType;
	}

	public void setCheckListDefaultType(String checkListDefaultType) {
		this.checkListDefaultType = checkListDefaultType;
	}

}
