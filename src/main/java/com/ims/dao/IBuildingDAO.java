package com.ims.dao;

import com.ims.entity.Building;

import java.util.List;
import java.util.Map;

public interface IBuildingDAO {
	public void add(String sql);
	public void del(String sql);
	public void alter(String sql);
	public List<Building> query(String sql);

	public void insert(Building building);
	public void delete(int id);
	public void update(Building building);
	public Building findById(int id);
	public List<Building> findAll();
	public List<Building> findAllbyFloor(String floor);
	public List<Building> findAllbyBuildingtype(int buildingtype);
	public float sumAreaByFloor(String floor);


}
