package com.zyl.centre.service;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Service;

public interface IServiceService extends IOperations<Service> {
	public List<Service> GetServiceByOrdid(int ordid);

	public List<Service> getServByAreaType(String city, String area,
			int productid, List<Integer> prodtypeid);
	public void DeleteTypeRelByid(int id);
	public void UpdateTypeRel(int service_id,int product_id, List<Integer> prodtypeid);
}
