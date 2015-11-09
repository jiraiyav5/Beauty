package com.zyl.centre.dao;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Shop;
import com.zyl.centre.entity.User;

@Repository("shopDao")
public class ShopDao extends HibernateDao<Shop> implements IShopDao {
	
	public ShopDao() {
		super();
		setClazz(Shop.class);
		setLog(LogFactory.getLog(ShopDao.class));
	}
	public Shop getByUid(String userid)
	{
		Integer id = Integer.valueOf(userid);
		try {
			String sql = "from shop where createuserid='" + id
					+ "'";
			@SuppressWarnings("unchecked")
			List<Shop> shops = getCurrentSession().createQuery(sql).list();
			log.debug("get successful");
			if (!shops.isEmpty()) {
				return shops.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
}
