package com.zyl.centre.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.stream.ImageInputStream;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.common.utils.TimeString;
import com.zyl.centre.common.utils.TokenUtils;
import com.zyl.centre.entity.*;
import com.zyl.centre.service.*;
public class ShopAction extends ActionSupport {
	
	private Shop shop;
	private Imgsrc shop_img;
	private Area area;
	private String areaname;
	private String cityname;
	private Token m_token;
	private String token;
	private String userid;
	private List<File> files;
	private List<String> filesFileName;
	private List<String> filesContentType;

	@Resource(name = "shopService")
	public IShopService shopService;
	@Resource(name = "ImgService")
	public IImgService imgService;
	@Resource(name = "AreaService")
	public IAreaService areaService;
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public List<String> getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}
	public List<String> getFilesContentType() {
		return filesContentType;
	}
	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Imgsrc getShop_img() {
		return shop_img;
	}
	public void setShop_img(Imgsrc shop_img) {
		this.shop_img = shop_img;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
	

	
	public void updateShopInfo() throws Exception {
		TimeString time= new TimeString();
		Map<String, Object> reMap = new HashMap<String, Object>();
		String root = ServletActionContext.getRequest().getRealPath("shop_upload");
		Map<String, Object> token_reMap= new HashMap<String, Object>();
		token_reMap = TokenUtils.manageToken(token);
		if(!token_reMap.get("message").equals("SUCCESS")){
		CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
		return;
		}
		
		if(null== ServletActionContext.getRequest().getParameter("files")||
				null== ServletActionContext.getRequest().getParameter("area")||
				null== ServletActionContext.getRequest().getParameter("city"))
		{
			reMap.put("ResultMessage", CommonUtils.JSONERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		 
		 System.out.println(root+'\n');
		 
		
		if(shop.getShopid() == null ||shop.getShopname() == null ||shop.getExt2() == null
				||shop.getShopdec() == null)
		{
			reMap.put("ResultMessage", CommonUtils.JSONERROR);
			CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
			return;
		}
		Area area_temp = areaService.GetByName(areaname, cityname);
		shop.setArea(area_temp);
		shopService.create(shop);
		 for(int i = 0; i<files.size(); i++){
			 InputStream is = new FileInputStream(files.get(i));
			 String filename=this.getFilesFileName().get(i)+time.getTimeString();
			 File destFile = new File(root, filename);
			 OutputStream os = new FileOutputStream(destFile);
			 byte[] buffer = new byte[400];
			 int length = 0;
			 while((length = is.read(buffer)) > 0)
			 {
				 os.write(buffer, 0, length);
			 }
			 is.close();
			 os.close();
			 String url = "shop_upload"+filename;
			 Imgsrc img_temp = new Imgsrc();
			 img_temp.setUrl(url);
			 img_temp.setShop(shop);
			 img_temp.setImgname(filename);
			 imgService.update(img_temp);
		 }
		 reMap.put("ResultMessage", CommonUtils.SUCCESS);
		 CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
		 return;
	}
	
	
	
	public void getShopInfo() throws Exception {
		Map<String, Object> token_reMap= new HashMap<String, Object>();
		Map<String, Object> reMap= new HashMap<String, Object>();
		Map<String, Object> imgMap= new HashMap<String, Object>();
		token_reMap = TokenUtils.manageToken(token);
		if(!token_reMap.get("message").equals("SUCCESS")){
		CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
		return;
		}
		Shop shop_temp = shopService.getByUid(userid);
		reMap.put("ResultMessage", CommonUtils.SUCCESS);
		reMap.put("shopid", shop_temp.getShopid());
		reMap.put("shopname", shop.getShopname());
		HashSet<Imgsrc> img_set = (HashSet<Imgsrc>)shop_temp.getImgsrcs();
		Iterator<Imgsrc> setImgsrcIt = img_set.iterator();
		Imgsrc img_temp = null;
		int i = 0;
		while(setImgsrcIt.hasNext())
		{
			img_temp = setImgsrcIt.next();
			imgMap.put("imgname"+i, img_temp.getImgname());
			imgMap.put("url"+i, img_temp.getUrl());
			imgMap.put("dec"+i, img_temp.getImgdec());
		}
		reMap.put("Shopimages", imgMap);
		reMap.put("telephone", shop_temp.getExt2());
		reMap.put("city", areaService.GetCityNameByid(shop_temp.getArea().getAreaid()));
		reMap.put("area", shop_temp.getArea().getAreaname());
		reMap.put("dec", shop_temp.getShopdec());
		CommonUtils.toJson(ServletActionContext.getResponse(), token_reMap);
		
		
	}
	
	
	

}
