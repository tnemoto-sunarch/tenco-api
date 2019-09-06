package jp.co.sunarch.tenco.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TCheckListResultPrimaryKey implements Serializable{

	@Column(name="check_list_no", nullable = false)
	private int checkListNo = -1;

	@Column(name="result_no", nullable = false)
	private int resultNo = -1;

}
