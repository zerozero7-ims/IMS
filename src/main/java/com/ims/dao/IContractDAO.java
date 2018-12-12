package com.ims.dao;

import com.ims.entity.Contract;

import java.util.List;

public interface IContractDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Contract> query(String sql);

	public void insert(Contract contract);
	public void delete(int id);
	public void update(Contract contract);
	public Contract findById(int id);
	public List<Contract> findAll();
}
