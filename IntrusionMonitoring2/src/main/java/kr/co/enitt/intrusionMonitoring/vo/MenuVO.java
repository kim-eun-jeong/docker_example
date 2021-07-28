package kr.co.enitt.intrusionMonitoring.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuVO {
	private String menuId;
	private String menuName;
	private String parentMenuId;
	private String menuLevel;
	private String menuOrder;
	private String menuUrl;
	private String menuUseYn;
	private String menuTitle;
	private String menuAuth;
	private String registerDate;
	private String registerId;
	private String registerIp;
	private String updateDate;
	private String updateId;
	private String updateIp;
	
	private String parentMenuName;
	private String parentMenuLevel;
	private String parentMenuOrder;
	private String parentMenuUrl;
	private String parentMenuUseYn;
	private String parentMenuTitle;
	private String parentMenuAuth;
	
	private String name;
	private List<String> nameList = new ArrayList<String>();
	private String url;
	private List<String> urlList = new ArrayList<String>();
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	public String getParentMenuName() {
		return parentMenuName;
	}
	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}
	public String getParentMenuLevel() {
		return parentMenuLevel;
	}
	public void setParentMenuLevel(String parentMenuLevel) {
		this.parentMenuLevel = parentMenuLevel;
	}
	public String getParentMenuOrder() {
		return parentMenuOrder;
	}
	public void setParentMenuOrder(String parentMenuOrder) {
		this.parentMenuOrder = parentMenuOrder;
	}
	public String getParentMenuUrl() {
		return parentMenuUrl;
	}
	public void setParentMenuUrl(String parentMenuUrl) {
		this.parentMenuUrl = parentMenuUrl;
	}
	public String getParentMenuUseYn() {
		return parentMenuUseYn;
	}
	public void setParentMenuUseYn(String parentMenuUseYn) {
		this.parentMenuUseYn = parentMenuUseYn;
	}
	public String getParentMenuTitle() {
		return parentMenuTitle;
	}
	public void setParentMenuTitle(String parentMenuTitle) {
		this.parentMenuTitle = parentMenuTitle;
	}
	public String getParentMenuAuth() {
		return parentMenuAuth;
	}
	public void setParentMenuAuth(String parentMenuAuth) {
		this.parentMenuAuth = parentMenuAuth;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuUseYn() {
		return menuUseYn;
	}
	public void setMenuUseYn(String menuUseYn) {
		this.menuUseYn = menuUseYn;
	}
	public String getMenuAuth() {
		return menuAuth;
	}
	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	
	
}