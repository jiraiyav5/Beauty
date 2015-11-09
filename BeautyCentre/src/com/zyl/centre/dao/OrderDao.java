package com.zyl.centre.dao;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Order;

@Repository("orderDao")
public class OrderDao extends HibernateDao<Order> implements IOrderDao{
	public OrderDao() {
		super();
		setClazz(Order.class);
		setLog(LogFactory.getLog(OrderDao.class));
	}
}
