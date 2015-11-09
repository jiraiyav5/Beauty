package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IUserDao;
import com.zyl.centre.entity.User;

@Service("userService")
public class UserService extends AbstractService<User> implements IUserService {
	@Resource(name = "usersDao")
	private IUserDao dao;

	public UserService() {
		super();
	}

	@Override
	protected IOperations<User> getDao() {
		return this.dao;
	}

	protected void setDao(IUserDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean checkByName(String username) {
		// TODO Auto-generated method stub
		return dao.checkByName(username);
	}

	@Override
	public User findOneByPass(String username, String pass) {
		// TODO Auto-generated method stub
		return dao.findOneByPass(username, pass);
	}
	public int GetUserIDByName(String username, String password) {
		return dao.GetUserIDByName(username, password);
	}



}
