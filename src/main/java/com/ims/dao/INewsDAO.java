package com.ims.dao;

import java.util.List;

import com.ims.entity.News;



public interface INewsDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<News> query(String sql);
	
	public void insert(News news);
	public void delete(int id);
	public void update(News news);
	public News findById(int id);
	public List<News> findAll();
	public int total(int type);
	

}
