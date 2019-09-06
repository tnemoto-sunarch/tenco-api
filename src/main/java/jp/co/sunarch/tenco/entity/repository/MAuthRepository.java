package jp.co.sunarch.tenco.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jp.co.sunarch.tenco.entity.MAuth;
import jp.co.sunarch.tenco.entity.MAuthPrimaryKey;

public interface MAuthRepository extends JpaRepository<MAuth, MAuthPrimaryKey>{

	public MAuth findByAuthIdAndAuthPassAndDelFlg(@Param("auth_id") String authId, @Param("auth_pass") String authPass, @Param("del_flg") int delFlg);

}
