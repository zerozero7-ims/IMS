package com.ims.dao;

import java.util.List;
import com.ims.entity.User;

public interface IUserDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<User> query(String sql);
	
	public void insert(User user);
	public void delete(int id);
	public void update(User user);
	public User findById(int id);
	public List<User> findAll();

	public Integer checkLogin(String username,String password);
	public void updatelogintime(User user);

}
