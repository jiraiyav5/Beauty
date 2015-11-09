package com.zyl.centre.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.AreaDao;
import com.zyl.centre.dao.IOrderDao;
import com.zyl.centre.entity.Area;
import com.zyl.centre.entity.Order;
@Service("AreaService")
public class AreaService  extends AbstractService<Area> implements IAreaService {

	@Resource(name = "AreaDao")
	private AreaDao dao;

	@Override
	protected IOperations<Area> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	@Override
	public Area findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Area entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Area update(Area entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Area entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub

	}
	public Area GetByName(String areaname, String cityname)
	{
		return dao.GetByName(areaname, cityname);
	}

	public String GetCityNameByid(Integer areaid)
	{
		return dao.GetCityNameByid(areaid);
	}

}
