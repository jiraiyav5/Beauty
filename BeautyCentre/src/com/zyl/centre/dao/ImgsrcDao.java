package com.zyl.centre.dao;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Imgsrc;

@Repository("imgsrcDao")
public class ImgsrcDao extends HibernateDao<Imgsrc> implements IImgsrcDao {
	public ImgsrcDao() {
		super();
		setClazz(Imgsrc.class);
		setLog(LogFactory.getLog(ImgsrcDao.class));
	}
}
