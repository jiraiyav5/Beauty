package com.zyl.centre.service;

import java.util.List;

import javax.annotation.Resource;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IImgsrcDao;

import com.zyl.centre.entity.Imgsrc;


import org.springframework.stereotype.Service;
@Service("ImgService")
public class ImgService extends AbstractService<Imgsrc> implements IImgService {

	@Resource(name = "imgsrcDao")
	private IImgsrcDao dao;

	@Override
	protected IOperations<Imgsrc> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	@Override
	public Imgsrc findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Imgsrc> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Imgsrc entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Imgsrc update(Imgsrc entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Imgsrc entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub

	}

}
