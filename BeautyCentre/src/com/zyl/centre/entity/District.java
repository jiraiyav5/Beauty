package com.zyl.centre.entity;

// Generated 2015-11-5 8:35:54 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * District generated by hbm2java
 */
@Entity
@Table(name = "district", catalog = "beautycentre")
public class District implements java.io.Serializable {

	private Integer districtid;
	private String districtname;
	private Integer postcode;
	private Integer parentid;
	private String districtdec;
	private String ext1;
	private Set<Area> areas = new HashSet<Area>(0);

	public District() {
	}

	public District(String districtname, Integer postcode, Integer parentid,
			String districtdec, String ext1, Set<Area> areas) {
		this.districtname = districtname;
		this.postcode = postcode;
		this.parentid = parentid;
		this.districtdec = districtdec;
		this.ext1 = ext1;
		this.areas = areas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "districtid", unique = true, nullable = false)
	public Integer getDistrictid() {
		return this.districtid;
	}

	public void setDistrictid(Integer districtid) {
		this.districtid = districtid;
	}

	@Column(name = "districtname", length = 20)
	public String getDistrictname() {
		return this.districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	@Column(name = "postcode")
	public Integer getPostcode() {
		return this.postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	@Column(name = "parentid")
	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Column(name = "districtdec", length = 65535)
	public String getDistrictdec() {
		return this.districtdec;
	}

	public void setDistrictdec(String districtdec) {
		this.districtdec = districtdec;
	}

	@Column(name = "ext1", length = 10)
	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

}
