package com.ims.dao;

import com.ims.entity.Payment;

import java.util.List;

public interface IPaymentDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Payment> query(String sql);

	public void insert(Payment payment);
	public void delete(int id);
	public void update(Payment payment);
	public Payment findById(int id);
	public List<Payment> findAll();
	public List<Payment> selectbyCid(int companyid);
	public void deletebyCid(int companyid);
}
