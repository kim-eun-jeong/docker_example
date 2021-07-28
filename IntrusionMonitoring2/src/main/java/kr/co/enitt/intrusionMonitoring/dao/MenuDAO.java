package kr.co.enitt.intrusionMonitoring.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.intrusionMonitoring.vo.MenuVO;

import java.util.List;

@Mapper
public interface MenuDAO{
	
	public List<MenuVO> getMenuList(MenuVO vo) throws Exception;
	public MenuVO getMenu(MenuVO vo) throws Exception;
	 
}
