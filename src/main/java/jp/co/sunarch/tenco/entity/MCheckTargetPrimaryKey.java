package jp.co.sunarch.tenco.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MCheckTargetPrimaryKey implements Serializable{

	@Column(name="target_id", nullable = false)
	private int targetId = -1;

}
