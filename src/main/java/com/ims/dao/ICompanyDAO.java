package com.ims.dao;

import com.ims.entity.Company;
import com.ims.entity.Payment;

import java.util.List;

public interface ICompanyDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Company> query(String sql);

	public void insert(Company company);
	public void delete(int id);
	public void update(Company company);
	public Company findById(int id);
	public List<Company> findAll();
	public Company findcurrByRoomnum(String roomnum);



}
