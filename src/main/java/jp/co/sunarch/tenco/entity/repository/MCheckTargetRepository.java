package jp.co.sunarch.tenco.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jp.co.sunarch.tenco.entity.MCheckTarget;
import jp.co.sunarch.tenco.entity.MCheckTargetPrimaryKey;

public interface MCheckTargetRepository extends JpaRepository<MCheckTarget, MCheckTargetPrimaryKey>{

	public MCheckTarget findByTargetId(@Param("target_id") int targetId);

	public List<MCheckTarget> findByDelFlgOrderBySort(@Param("del_flg") int delFlg);
}
