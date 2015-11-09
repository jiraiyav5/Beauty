package com.zyl.centre.service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.User;

public interface IUserService extends IOperations<User> {

	boolean checkByName(final String username);

	User findOneByPass(final String username, final String pass);
	int GetUserIDByName(String username, String password);


}
