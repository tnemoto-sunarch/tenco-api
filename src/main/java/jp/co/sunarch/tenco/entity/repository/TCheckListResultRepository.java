package jp.co.sunarch.tenco.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sunarch.tenco.entity.TCheckListResult;
import jp.co.sunarch.tenco.entity.TCheckListResultPrimaryKey;

public interface TCheckListResultRepository extends JpaRepository<TCheckListResult, TCheckListResultPrimaryKey>{

	public List<TCheckListResult> findByCheckListNoAndDelFlgOrderByResultNo(int checkListNo, int delFlg);

	public TCheckListResult findByCheckListNoAndResultNo(int checkListNo, int resultNo);
}
