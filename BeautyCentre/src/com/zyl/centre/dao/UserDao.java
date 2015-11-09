package com.zyl.centre.dao;

import java.util.List;


import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;



import com.zyl.centre.entity.User;

@Repository("usersDao")
public class UserDao extends HibernateDao<User> implements IUserDao {

	public UserDao() {
		super();
		setClazz(User.class);
		setLog(LogFactory.getLog(UserDao.class));
	}

	@Override
	public User findOneByPass(String username, String pass) {
		// TODO Auto-generated method stub
		log.debug("getting User instance with username:" + username + " pass:"
				+ pass);
		try {
			String sql = "from User where username='" + username
					+ "' and password='" + pass + "'";
			@SuppressWarnings("unchecked")
			List<User> users = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!users.isEmpty()) {
				return users.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
	
	public int GetUserIDByName(String username, String password) {
		System.out.println("GetUserIDByName" +username);
		String sql ="from User where username='" + username
				+"' and password='"+ password+"'";
		try{
			List<User> users = getCurrentSession().createQuery(sql).list();
			if(!users.isEmpty()) {
				return users.get(0).getUserid();
			}
			return -1;
		} catch (RuntimeException e) {
			log.error("GetUserIDByName failed", e);
			throw e;
		}
	};

	@Override
	public boolean checkByName(String username) {
		// TODO Auto-generated method stub
		log.debug("checking User instance with username:" + username);
		try {
			String sql = "from User where username='" + username + "' ";
			@SuppressWarnings("unchecked")
			List<User> users = getCurrentSession().createQuery(sql).list();
			log.debug("check successful");
			if (!users.isEmpty()) {
				return true;
			}
			return false;
		} catch (RuntimeException re) {
			log.error("check  failed", re);
			throw re;
		}
	}
}
