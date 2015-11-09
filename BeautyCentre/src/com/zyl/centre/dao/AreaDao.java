package com.zyl.centre.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.zyl.centre.entity.Area;
import com.zyl.centre.entity.District;
import com.zyl.centre.entity.User;



@Repository("areaDao")
public class AreaDao extends HibernateDao<Area> implements IAreaDao {
	public AreaDao() {
		super();
		setClazz(Area.class);
		setLog(LogFactory.getLog(AreaDao.class));
	}
	
	public Area GetByName(String areaname, String cityname) {
		// TODO Auto-generated method stub
		Area area_temp = null;
		try {
			String sql = "from district where districtname='" + cityname
					+"'";
			@SuppressWarnings("unchecked")
			List<District> districts = getCurrentSession().createQuery(sql).list();
			
			if (!districts.isEmpty()) {
				District d_temp = districts.get(0);
				HashSet<Area> area_hash = (HashSet<Area>) d_temp.getAreas();
				Iterator<Area> setAreaIt = area_hash.iterator();
				while(setAreaIt.hasNext())
				{
					area_temp = setAreaIt.next();
					if(area_temp.getAreaname().equals(areaname))
					if(setAreaIt.next().getAreaname().equals(areaname))
						break;
				}
				if(area_temp.getAreaname().equals(areaname))
					return area_temp;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}
	
	
	public String GetCityNameByid(Integer areaid) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String sql = "SELECT b.districtname city,a.areaname area FROM AREA AS a, district AS b WHERE a.districtid=b.districtid AND a.areaid ='"
					+ areaid + "'";
			org.hibernate.SQLQuery query = getCurrentSession().createSQLQuery(
					sql);
			query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> listmap = query.list();
			if (listmap != null && !listmap.isEmpty()) {
				map = listmap.get(0);
				return map.get("city").toString();
			}
			return null;
		} catch (RuntimeException re) {
			log.error("get  failed", re);
			throw re;
		}
	}

	

	
}
