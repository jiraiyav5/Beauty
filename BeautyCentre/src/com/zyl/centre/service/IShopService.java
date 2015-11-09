package com.zyl.centre.service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Shop;


public interface IShopService extends IOperations<Shop>{
	
	public Shop getByUid(String userid);

}
