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
	public void updateRepair(Flow flow);//更新维修申请表，不包含验收单字段
	public void updateReceiving(Flow flow);//更新维修申请表，只更新验收单部分字段
}
