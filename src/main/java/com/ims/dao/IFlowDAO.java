package com.ims.dao;

import com.ims.entity.Flow;
import java.util.List;

public interface IFlowDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Flow> query(String sql);

	public void insert(Flow flow);
	public void delete(int id);
	public void update(Flow flow);
	public Flow findById(int id);
	public List<Flow> findAll();
	public List<Flow> findByType(int type);
	public Integer checkflow(String companyname);
}
