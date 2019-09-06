package jp.co.sunarch.tenco.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@IdClass(TCheckListResultPrimaryKey.class)
@Table(name = "t_checklist_result")
public class TCheckListResult extends AbstractEntity{

	@Id
	@Column(name="check_list_no", nullable = false)
	private int checkListNo = -1;

	@Id
	@Column(name="result_no", nullable = false)
	private int resultNo = -1;

	@Column(name="name", nullable = false)
	@Size(max = 255)
	private String name = null;

	@Column(name="status", nullable = false)
	private boolean status = false;

	@Column(name="check_time", nullable = true)
	private Date checkTime = null;

	public int getCheckListNo() {
		return checkListNo;
	}

	public void setCheckListNo(int checkListNo) {
		this.checkListNo = checkListNo;
	}

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

	public int getResultNo() {
		return resultNo;
	}

	public void setResultNo(int resultNo) {
		this.resultNo = resultNo;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
}
