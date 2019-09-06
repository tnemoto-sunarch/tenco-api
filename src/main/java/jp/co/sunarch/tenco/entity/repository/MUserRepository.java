package jp.co.sunarch.tenco.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sunarch.tenco.entity.MUser;
import jp.co.sunarch.tenco.entity.MUserPrimaryKey;

public interface MUserRepository extends JpaRepository<MUser, MUserPrimaryKey>{

	public MUser findByUserIdAndDelFlg(@Param("user_id") String userId, @Param("del_flg") int delFlg);

	@Query(value = "select max(user_no) + 1 from m_user",nativeQuery = true)
	public Number findByMaxUserNo();

}
