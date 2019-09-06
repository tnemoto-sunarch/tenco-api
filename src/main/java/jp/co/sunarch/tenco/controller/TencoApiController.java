package jp.co.sunarch.tenco.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sunarch.tenco.dto.JsonRequest;
import jp.co.sunarch.tenco.dto.JsonResponse;
import jp.co.sunarch.tenco.dto.JsonResponseAuthInfo;
import jp.co.sunarch.tenco.dto.JsonResponseCheckList;
import jp.co.sunarch.tenco.dto.JsonResponseCheckListDetail;
import jp.co.sunarch.tenco.dto.JsonResponseCheckListResult;
import jp.co.sunarch.tenco.dto.JsonResponseUserInfo;
import jp.co.sunarch.tenco.entity.MAuth;
import jp.co.sunarch.tenco.entity.MUser;
import jp.co.sunarch.tenco.entity.TCheckList;
import jp.co.sunarch.tenco.entity.TCheckListResult;
import jp.co.sunarch.tenco.service.TencoApiService;

@Controller
@ResponseBody
@RequestMapping("/tenco/api/v1")
@CrossOrigin
public class TencoApiController {

	private Log logger = LogFactory.getLog(TencoApiController.class);

	@Autowired
	TencoApiService service;

	@RequestMapping(value = "sample", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> sample(@RequestBody JsonRequest req) {
		logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId());

		JsonResponse res = new JsonResponse();

		res.setResultCode(0);
		res.setMessage("Hello");
		res.setProccess(new Date());

		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> userLogin(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId());
			logger.info("★★ auth_id=" + req.getAuthInfo().getId() + ", auth_pass=" + req.getAuthInfo().getPass());

			MAuth auth = service.searchAuth(req.getAuthInfo().getId(), req.getAuthInfo().getPass());

			if(auth != null) {
				res.setAuthInfo(new JsonResponseAuthInfo());
				res.getAuthInfo().setLogin(true);
				res.getAuthInfo().setType(auth.getAuthType());

				service.registUser(req.getUserId());
			} else {
				res.setAuthInfo(new JsonResponseAuthInfo());
				res.getAuthInfo().setLogin(false);

				logger.info("-- Non-Authoritative Information.");
			}
			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "user/info", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> userCheck(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId());
			MUser user = service.searchUser(req.getUserId());
			if(user != null) {
				res.setUserInfo(new JsonResponseUserInfo());
				res.getUserInfo().setNo(user.getUserNo());
				res.getUserInfo().setId(user.getUserId());
				res.getUserInfo().setName(user.getUserName());
				res.getUserInfo().setAdmin(service.isAdmin(user));
				res.getUserInfo().setLogin(true);
			} else {
				res.setUserInfo(new JsonResponseUserInfo());
				res.getUserInfo().setAdmin(false);
				res.getUserInfo().setLogin(false);

				logger.info("-- Non-Authoritative Information.");
			}
			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/list", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> checklist(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId());

			List<TCheckList> checkList = service.searchEnableCheckList();

			res.setCheckList(new ArrayList<JsonResponseCheckList>());
			for(TCheckList c : checkList) {
				JsonResponseCheckList data = new JsonResponseCheckList();
				data.setId(c.getCheckListId());
				data.setName(c.getCheckListName());
				data.setType(c.getCheckListType());
				data.setStatus(c.getStatus());
				res.getCheckList().add(data);
			}

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/add", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> addCheckList(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId());

			service.createCheckList(req.getAddCheckList().getName(), req.getAddCheckList().getType(), req.getUserId());

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> checklist(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			TCheckList checkList = service.searchCheckList(checkListId);
			List<TCheckListResult> resultList = service.searchEnableCheckListResult(checkList.getCheckListNo());

			JsonResponseCheckListDetail jsonDetail = new JsonResponseCheckListDetail();
			res.setCheckListDetail(jsonDetail);

			jsonDetail.setId(checkList.getCheckListId());
			jsonDetail.setTitle(checkList.getCheckListName());
			jsonDetail.setType(checkList.getCheckListType());
			jsonDetail.setStatus(checkList.getStatus());
			jsonDetail.setOpenTime(checkList.getOpenTime());
			jsonDetail.setCloseTime(checkList.getCloseTime());
			jsonDetail.setMemo(checkList.getMemo());
			jsonDetail.setResultList(new ArrayList<JsonResponseCheckListResult>());

			int checked = 0;
			for(TCheckListResult result : resultList) {
				JsonResponseCheckListResult data = new JsonResponseCheckListResult();
				data.setNo(result.getResultNo());
				data.setName(result.getName());

				data.setStatus(result.isStatus());
				if(result.isStatus()) checked ++;

				data.setCheckTime(result.getCheckTime());

				jsonDetail.getResultList().add(data);
			}
			jsonDetail.setCurCount(checked);
			jsonDetail.setTotalCount(jsonDetail.getResultList().size());

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}/open", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> openChecklist(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			service.openCheckList(checkListId);

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}/check", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> checkChecklist(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId + ", no=" + req.getCheckTarget().getNo());

			service.checkCheckList(checkListId, req.getCheckTarget().getNo(), req.getCheckTarget().isStatus());
			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}


	@RequestMapping(value = "checklist/{checkListId}/close", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> closeChecklist(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			service.closeCheckList(checkListId);

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}/chtype", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> changeChecklist(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			service.changeTypeCheckList(checkListId);

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}/updmemo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> updateCheckListMemo(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			service.updateCheckListMemo(checkListId, req.getUpdateMemo().getMemo());

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}/reset", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> resetCheckListMemo(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			boolean isAdmin = service.isAdmin(req.getUserId());

			if(isAdmin) {
				logger.info("-- admin. process continue.");
				service.resetCheckList(checkListId);
			} else {
				logger.info("-- not admin. process skip.");
			}

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "checklist/{checkListId}/reopen", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> reopenCheckListMemo(@RequestBody JsonRequest req, @PathVariable("checkListId") String checkListId) {
		JsonResponse res = new JsonResponse();
		try {
			logger.info("★★ uid=" + req.getUserId() + ", rid=" + req.getRequestId() + ", cid=" + checkListId);

			boolean isAdmin = service.isAdmin(req.getUserId());

			if(isAdmin) {
				logger.info("-- admin. process continue.");
				service.openCheckList(checkListId);
			} else {
				logger.info("-- not admin. process skip.");
			}

			res.setResultCode(0);
			res.setMessage("success");
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			res.setResultCode(999);
			res.setMessage(e.getMessage());
		}

		res.setProccess(new Date());
		return ResponseEntity.ok(res);
	}

}
