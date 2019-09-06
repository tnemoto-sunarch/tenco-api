package jp.co.sunarch.tenco.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sunarch.tenco.entity.TCheckList;
import jp.co.sunarch.tenco.entity.TCheckListPrimaryKey;

public interface TCheckListRepository extends JpaRepository<TCheckList, TCheckListPrimaryKey>{

	public TCheckList findByCheckListNo(int checkListNo);

	public TCheckList findByCheckListId(String checkListId);

	public List<TCheckList> findByStatusOrderByCheckListNo(@Param("status") String status);

	public List<TCheckList> findByDelFlgOrderByCheckListNo(@Param("del_flg") int delFlg);

	@Query(value = "select max(check_list_no) + 1 from t_checklist",nativeQuery = true)
	public Number findByMaxCheckListNo();

}
