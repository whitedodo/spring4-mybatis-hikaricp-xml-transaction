package com.website.example.vo;

import java.sql.Date;

public class FoodMenuVO {

	private long id;
	private String name;
	private long price;
	private long store_id;
	private long cnt;
	private Date regidate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getStore_id() {
		return store_id;
	}
	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	
	
	
}
