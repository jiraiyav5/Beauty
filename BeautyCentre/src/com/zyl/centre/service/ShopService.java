package com.zyl.centre.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IShopDao;
import com.zyl.centre.entity.Shop;

@Service("shopService")
public class ShopService extends AbstractService<Shop> implements IShopService {

	@Resource(name = "shaopDao")
	private IShopDao dao;

	public ShopService() {
		super();
	}

	@Override
	protected IOperations<Shop> getDao() {
		return this.dao;
	}
	public Shop getByUid(String userid){
		return dao.getByUid(userid);
	}
}
