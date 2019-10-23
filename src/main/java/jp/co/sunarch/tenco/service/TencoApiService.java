package jp.co.sunarch.tenco.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sunarch.tenco.entity.MAuth;
import jp.co.sunarch.tenco.entity.MCheckTarget;
import jp.co.sunarch.tenco.entity.MUser;
import jp.co.sunarch.tenco.entity.TCheckList;
import jp.co.sunarch.tenco.entity.TCheckListResult;
import jp.co.sunarch.tenco.entity.repository.MAuthRepository;
import jp.co.sunarch.tenco.entity.repository.MCheckTargetRepository;
import jp.co.sunarch.tenco.entity.repository.MUserRepository;
import jp.co.sunarch.tenco.entity.repository.TCheckListRepository;
import jp.co.sunarch.tenco.entity.repository.TCheckListResultRepository;

@Service
@Transactional
public class TencoApiService {

	private Log logger = LogFactory.getLog(TencoApiService.class);

	@Autowired
	TCheckListRepository checklistRepo;
	@Autowired
	TCheckListResultRepository resultRepo;
	@Autowired
	MCheckTargetRepository targetRepo;

	@Autowired
	MUserRepository userRepo;
	@Autowired
	MAuthRepository authRepo;

	public MUser searchUser(String userId) {
		return userRepo.findByUserIdAndDelFlg(userId, 0);
	}

	public MAuth searchAuth(String authId, String authPass) {
		return authRepo.findByAuthIdAndAuthPassAndDelFlg(authId, authPass, 0);
	}

	public void registUser(String userId, String authType) {
		MUser user = searchUser(userId);
		if(user != null) {
			logger.info("user already exists. user info updating.");
			user.setUserType(authType);
			user.setDelFlg(0);
			user.setLastUpdateUserId(userId);
		} else {
			logger.info("user not exists. user info creating.");
			user = new MUser();
			Number mno = userRepo.findByMaxUserNo();
			if(mno != null) {
				user.setUserNo(mno.intValue());
			} else {
				user.setUserNo(1);
			}
			user.setUserId(userId);
			user.setUserName(null);
			user.setUserType(authType);
			user.setDelFlg(0);
			user.setCreateUserId(userId);
			user.setLastUpdateUserId(userId);
		}

		userRepo.save(user);
	}


	public List<TCheckList> searchEnableCheckList(){
		return checklistRepo.findByDelFlgOrderByCheckListNo(0);
	}

	public TCheckList searchCheckList(String checkListId) {
		return checklistRepo.findByCheckListId(checkListId);
	}

	public List<TCheckListResult> searchEnableCheckListResult(int checkListNo){
		return resultRepo.findByCheckListNoAndDelFlgOrderByResultNo(checkListNo, 0);
	}

	public void openCheckList(String checkListId) {
		TCheckList checklist = searchCheckList(checkListId);
		this.openCheckList(checklist);
	}

	public void openCheckList(TCheckList checklist) {
		logger.info("open checklist (id=" + checklist.getCheckListId() + ", no=" + checklist.getCheckListNo() + ")");

		List<TCheckListResult> resultList = resultRepo.findByCheckListNoAndDelFlgOrderByResultNo(checklist.getCheckListNo(), 0);

		if(resultList.size() == 0) {
			List<MCheckTarget> targetList = targetRepo.findByDelFlgOrderBySort(0);

			logger.info("-- target: " + targetList.size());

			List<TCheckListResult> entityList = new ArrayList<TCheckListResult>();

			for(MCheckTarget t : targetList) {
				TCheckListResult data = new TCheckListResult();
				data.setCheckListNo(checklist.getCheckListNo());
				data.setResultNo(t.getSort());
				data.setName(t.getTargetName());
				data.setStatus(false);

				entityList.add(data);
			}

			resultRepo.saveAll(entityList);
		} else {
			logger.info("-- A list has already been created.(" + resultList.size() + ")");
		}

		checklist.setStatus("10");
		checklist.setOpenTime(new Date());

		checklistRepo.save(checklist);
	}

	public void checkCheckList(String checkListId, int resultNo, boolean status) {
		TCheckList checklist = searchCheckList(checkListId);
		TCheckListResult result = resultRepo.findByCheckListNoAndResultNo(checklist.getCheckListNo(), resultNo);

		result.setCheckTime(new Date());
		result.setStatus(status);

		resultRepo.save(result);
	}

	public void closeCheckList(String checkListId) {
		TCheckList checklist = searchCheckList(checkListId);

		checklist.setStatus("20");
		checklist.setCloseTime(new Date());;

		checklistRepo.save(checklist);
	}

	public void changeTypeCheckList(String checkListId) {
		TCheckList checklist = searchCheckList(checkListId);

		logger.info("change type checklist (id=" + checklist.getCheckListId() + ", no=" + checklist.getCheckListNo() + ")");
		logger.info("-- type: " + checklist.getCheckListType());

		if(checklist.getCheckListType().equals("10")) {
			//チェックリスト確認(10)⇒人数確認(20)
			checklist.setCheckListType("20");
			checklistRepo.save(checklist);
		} else if(checklist.getCheckListType().equals("20")) {
			//人数確認(20)⇒チェックリスト確認(10)
			checklist.setCheckListType("10");
			checklistRepo.save(checklist);
		}
		//忘れ物チェック(30)からの変更はしない
	}

	public void updateCheckListMemo(String checkListId, String memo) {
		TCheckList checklist = searchCheckList(checkListId);

		logger.info("update memo (id=" + checklist.getCheckListId() + ", no=" + checklist.getCheckListNo() + ")");

		checklist.setMemo(memo);
		checklistRepo.save(checklist);
	}

	public void resetCheckList(String checkListId) {
		TCheckList checklist = searchCheckList(checkListId);

		logger.info("reset checklist (id=" + checklist.getCheckListId() + ", no=" + checklist.getCheckListNo() + ")");

		List<TCheckListResult> resultList = resultRepo.findByCheckListNoAndDelFlgOrderByResultNo(checklist.getCheckListNo(), 0);

		if(resultList.size() > 0) {
			resultRepo.deleteAll(resultList);
		}

		checklist.setStatus("00");
		checklist.setCheckListType(checklist.getCheckListDefaultType());
		checklist.setOpenTime(null);
		checklist.setCloseTime(null);

		checklistRepo.save(checklist);
	}

	public void createCheckList(String checkListName, String checkListType, String userId) {
		TCheckList checklist = new TCheckList();
		checklist.setCheckListNo(checklistRepo.findByMaxCheckListNo().intValue());
		checklist.setCheckListId(RandomStringUtils.randomAlphanumeric(32));
		checklist.setCheckListName(checkListName);
		checklist.setCheckListType(checkListType);
		checklist.setCheckListDefaultType(checkListType);
		checklist.setStatus("00");
		checklist.setDelFlg(0);
		checklist.setCreateUserId(userId);
		checklist.setLastUpdateUserId(userId);

		checklistRepo.save(checklist);
	}

	public boolean isAdmin(String uid) {
		MUser user = searchUser(uid);
		return isAdmin(user);
	}

	public boolean isAdmin(MUser user) {
		if(user.getUserType().equals("10")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAdmin(MAuth auth) {
		if(auth.getAuthType().equals("10")) {
			return true;
		} else {
			return false;
		}
	}

	public List<MCheckTarget> searchCheckTarget() {
		return targetRepo.findByDelFlgOrderBySort(0);
	}

	public MCheckTarget searchCheckTarget(int targetId) {
		return targetRepo.findByTargetId(targetId);
	}

	public void updateCheckTarget(int targetId, String name, int sort, String userId) {
		MCheckTarget target = searchCheckTarget(targetId);

		if(target != null) {
			target.setTargetName(name);
			target.setSort(sort);
			target.setLastUpdateUserId(userId);
		} else {
			target = new MCheckTarget();
			target.setTargetId(targetId);
			target.setTargetName(name);
			target.setSort(sort);
			target.setDelFlg(0);
			target.setCreateUserId(userId);
			target.setLastUpdateUserId(userId);
		}

		targetRepo.save(target);
	}

	public void deleteCheckTarget(int targetId, String userId) {
		MCheckTarget target = searchCheckTarget(targetId);

		if(target != null) {
			logger.info("target data deleted.[" + targetId + "]");
			targetRepo.delete(target);
		} else {
			logger.info("not exists data");
		}
	}
}
