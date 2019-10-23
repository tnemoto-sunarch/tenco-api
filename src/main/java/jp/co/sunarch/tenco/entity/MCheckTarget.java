package jp.co.sunarch.tenco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@IdClass(MCheckTargetPrimaryKey.class)
@Table(name = "m_check_target")
public class MCheckTarget extends AbstractEntity{

	@Id
	@Column(name="target_id", nullable = false)
	private int targetId = -1;

	@Column(name="target_name", nullable = true)
	@Size(max = 255)
	private String targetName = null;

	@Column(name="sort", nullable = false)
	private int sort = -1;

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
