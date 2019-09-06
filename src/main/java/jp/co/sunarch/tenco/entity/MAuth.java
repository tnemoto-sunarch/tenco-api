package jp.co.sunarch.tenco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@IdClass(MAuthPrimaryKey.class)
@Table(name = "m_auth")
public class MAuth extends AbstractEntity{

	@Id
	@Column(name="auth_no", nullable = false)
	private int authNo = -1;

	@Column(name="auth_id", nullable = false)
	@Size(max = 255)
	private String authId = null;

	@Column(name="auth_pass", nullable = false)
	@Size(max = 255)
	private String authPass = null;

	@Column(name="auth_type", nullable = false)
	@Size(max = 2)
	private String authType = null;

	public int getAuthNo() {
		return authNo;
	}

	public void setAuthNo(int authNo) {
		this.authNo = authNo;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthPass() {
		return authPass;
	}

	public void setAuthPass(String authPass) {
		this.authPass = authPass;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

}
