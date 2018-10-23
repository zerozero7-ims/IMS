package com.ims.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository(value = "entMapper") 
public interface EntMapper {

	List<Map<String,Object>> getLmTree(@Param("showid") String showid);
	List<Map<String,Object>> usrGetLm();
	List<Map<String,Object>> getTblZiduan(String tbl, String db);//db:写CL.DB即可
	//根据父节点和分页数来查找栏目节点
	List<Map<String,Object>> getLmList(int pId, Integer s, Integer e);
	int cntLmList(int pId);


	int ins(Map<String, Object> pmp);
	int add(@Param("sql") String s);
	int del(@Param("sql") String s);
	int upd(@Param("sql") String s);
	int cnt(@Param("sql") String s);
	int vpx(@Param("sql") String s);
	List<Map<String,Object>> r(@Param("sql") String s);
	List<Map<Object,Object>> ro(@Param("sql") String s);
	Map<String,Object> obj(@Param("sql") String s);

	
	List<Map<String, Object>> getnav();
	List<Map<String, Object>> getzlmlist(Object id);
} 
