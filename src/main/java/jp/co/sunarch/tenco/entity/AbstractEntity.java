package jp.co.sunarch.tenco.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractEntity {

	/** 削除フラグ */
	@Column(name="del_flg", nullable = false)
	private int delFlg = 0;
	/** 作成者 */
	@Column(name="create_user_id", nullable = false)
	@Size(max = 255)
	private String createUserId = null;
	/** 作成日時 */
	@Column(name="create_date", nullable = false)
	private Date createDate = null;
	/** 最終更新者 */
	@Column(name="last_update_user_id", nullable = false)
	@Size(max = 255)
	private String lastUpdateUserId = null;
	/** 最終更新日時 */
	@Column(name="last_update_date", nullable = false)
	private Date lastUpdateDate = null;

	@PrePersist
	public void onPrePersist() {
		setCreateDate(new Date());
		setLastUpdateDate(new Date());
	}

	@PreUpdate
	public void onPreUpdate() {
		setLastUpdateDate(new Date());
	}

	public int getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
