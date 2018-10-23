package com.ims.dao;

import java.util.List;

import com.ims.entity.Menu;



public interface IMenuDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Menu> query(String sql);
	
	public void insert(Menu menu);
	public void delete(int id);
	public void update(Menu menu);
	public Menu findByMenuId(int menuid);
	public List<Menu> findByParentId(int parentid);
	public List<Menu> findAll();
	

}
