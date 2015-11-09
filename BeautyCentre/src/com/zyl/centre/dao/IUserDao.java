package com.zyl.centre.dao;


import com.google.common.base.FinalizablePhantomReference;
import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.User;

public interface IUserDao extends IOperations<User> {
    //�����е�DAO��ʵ�ֻ�Ĳ����ӿ�IOperations
    //����ʵ��IOperations�еĻ����֮�⣬�ض���DAOҪʵ��������������ڶ�Ӧ�Ľӿ�DAO�ж��巽����
    //�˴�UserDao�Ľӿ�IUserDao����Ҫʵ������
	boolean checkByName(final String username);
	User  findOneByPass(final String username,final String pass);
	int GetUserIDByName(final String username, final String password);
}
