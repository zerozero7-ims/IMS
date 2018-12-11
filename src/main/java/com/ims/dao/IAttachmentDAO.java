package com.ims.dao;

import com.ims.entity.Attachment;
import com.ims.entity.Payment;

import java.util.List;

public interface IAttachmentDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Attachment> query(String sql);

	public void insert(Attachment attachment);
	public void delete(int id);
	public void update(Attachment attachment);
	public Attachment findById(int id);
	public List<Attachment> findAll();
	public List<Attachment> selectbyPid(int pid);
	public void deletebyPid(int pid);
}
