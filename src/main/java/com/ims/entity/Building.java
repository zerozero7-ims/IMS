package com.ims.entity;



import java.io.Serializable;

@SuppressWarnings("serial")
public class Building implements Serializable{
	private int id;
	private String roomnum;
	private float area;
	private float unitprice;
	private int state;
	private String term;
	private String purpose;
	private int buildingtype;
	private int usetype;
	private String floor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(float unitprice) {
		this.unitprice = unitprice;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getBuildingtype() {
		return buildingtype;
	}

	public void setBuildingtype(int buildingtype) {
		this.buildingtype = buildingtype;
	}

	public int getUsetype() {
		return usetype;
	}

	public void setUsetype(int usetype) {
		this.usetype = usetype;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
}
