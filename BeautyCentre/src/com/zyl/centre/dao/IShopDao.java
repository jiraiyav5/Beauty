package com.zyl.centre.dao;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Shop;


public interface IShopDao extends IOperations<Shop>{
	public Shop getByUid(String userid);

}
