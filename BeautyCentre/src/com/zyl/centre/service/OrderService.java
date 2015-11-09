package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IOrderDao;
import com.zyl.centre.entity.Order;

@Service("orderService")
public class OrderService extends AbstractService<Order> implements
		IOrderService {

	@Resource(name = "orderDao")
	private IOrderDao dao;

	@Override
	protected IOperations<Order> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
}
