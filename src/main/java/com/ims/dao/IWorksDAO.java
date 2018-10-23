package com.ims.dao;

import java.util.List;

import com.ims.entity.Works;


public interface IWorksDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Works> query(String sql);
	
	public void insert(Works works);
	public void delete(int id);
	public void update(Works works);
	public Works findById(int id);
	public List<Works> findAll();

}
