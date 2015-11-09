package com.zyl.centre.action;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.entity.Token;
import com.zyl.centre.entity.User;
import com.zyl.centre.service.*;

public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Log log = LogFactory.getLog(LoginAction.class);
	@Resource(name = "userservice")
	private IUserService userservice;

	@Resource(name = "tokenService")
	private ITokenService tokenService;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
	

	public void login() throws IOException {
		try {
			/*
			 * HttpServletResponse response =
			 * ServletActionContext.getResponse();
			 * response.setContentType("text/json; charset=utf-8");
			 * response.setHeader("Cache-Control", "no-cache"); // ȡ�����������
			 * PrintWriter out = response.getWriter();
			 * out.println(ObjToJson(list)); out.flush(); out.close();
			 */
			Map<String, Object> reMap = new HashMap<String, Object>();
			/*
			 * User user = userservice.findOneByPass("zhangyili", "111");
			 * 
			 * if (user != null) { String tokencode = getTokenCode(); Token
			 * token = new Token(); token.setTokencode(tokencode);
			 * token.setUser(user); // SimpleDateFormat dateTimeFormat = new //
			 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar cal =
			 * Calendar.getInstance();// ȡ��ǰ���ڡ� Date currentDate =
			 * cal.getTime(); System.out.println("----ʱ��----");
			 * System.out.println(currentDate.toString());
			 * cal.add(Calendar.DAY_OF_MONTH, +30);// ȡ��ǰ���ڵĺ�30��.
			 * System.out.println(cal.getTime().toString());
			 * token.setCreatedatetime(currentDate);
			 * token.setExpiredatetime(cal.getTime());
			 * tokenService.create(token); reMap.put("ResultMassage",
			 * CommonUtils.SUCCESS); reMap.put("token", tokencode);
			 * reMap.put("userid", user.getUserid());
			 * CommonUtils.setSessionMap(user.getUserid(), tokencode); } else {
			 * reMap.put("ResultMassage", CommonUtils.ERROR); }
			 */
			User user1 = userservice.findOneByPass(user.getUsername(), user.getPassword());//��ȡjson������������
			if (user1 != null) {
				String tokencode = getTokenCode();
				Token token = new Token();
				token.setUserid(user1.getUserid());
				token.setTokencode(tokencode);
				Calendar cal = Calendar.getInstance();// ȡ��ǰ���ڡ�
				Date currentDate = cal.getTime();
				cal.add(Calendar.DAY_OF_MONTH, +30);// ȡ��ǰ���ڵĺ�30��.
				token.setCreatedatetime(currentDate);
				token.setExpiredatetime(cal.getTime());
				tokenService.createTokenCode(token);
				CommonUtils.setSessionMap(user1.getUserid(), tokencode);
				reMap.put("ResultMassage", CommonUtils.SUCCESS);
				reMap.put("token", tokencode);
				reMap.put("userid", user1.getUserid());
				CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			} else {
				reMap.put("ResultMassage", CommonUtils.ERROR);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONObject getJson() {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = ServletActionContext.getRequest()
					.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("client json data=" + json);

		JSONObject jsonObj = JSONObject.fromObject(json.toString());
		/*
		 * BufferedReader br = new BufferedReader(new InputStreamReader(
		 * (ServletInputStream) request.getInputStream())); String line = null;
		 * StringBuffer sb = new StringBuffer(); while ((line = br.readLine())
		 * != null) { sb.append(line); } String appMsg=sb.toString();
		 */
		return jsonObj;
	}

	/*
	 * public JSONArray ObjToJson(List<User> list) { JSONArray json = new
	 * JSONArray(); for (User user : list) { JSONObject jo = new JSONObject();
	 * jo.put("id", user.getUsername()); jo.put("title", user.getPassword());
	 * json.add(jo); } JSONObject jo = new JSONObject(); jo.put("status", 1);
	 * JSONObject n1 = new JSONObject(); n1.put("users", json);
	 * 
	 * JSONArray json2 = new JSONArray(); json2.add(jo); json2.add(n1); return
	 * json2; }
	 */
	public String unlogin() {
		System.out.println("-----����unlogin-------");
		Map<String, Object> map = new HashMap<String, Object>();
		String status = null;
		List<User> list = userservice.findAll();
		if (list.size() > 0) {
			status = "1";
			map.put("username", list.get(0).getRealname());
			map.put("pass", list.get(0).getPassword());
		} else {
			status = "null";
		}
		map.put("status", status);
		Map<String, Object> map1 = CommonUtils.getsetSessionMap();
		System.out.println(map1.toString());
		Token saveToken = tokenService.findOneByCode(map1.get("tokenCode")
				.toString());
		System.out.println("saveToken--------" + saveToken.getTokencode()
				+ "time" + saveToken.getCreatedatetime());

		System.out.println(map1.toString());

		return SUCCESS;
	}

	public String getTokenCode() {
		log.debug("getting token code");
		String tokencode = TokenUtils.generateValue();
		if (tokenService.findOneByCode(tokencode) != null) {
			getTokenCode();
		}
		log.info("get token success");
		return tokencode;
	}

}
