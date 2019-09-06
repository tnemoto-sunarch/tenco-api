package jp.co.sunarch.tenco.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MAuthPrimaryKey implements Serializable{

	@Column(name="auth_no", nullable = false)
	private int authNo = -1;

}
