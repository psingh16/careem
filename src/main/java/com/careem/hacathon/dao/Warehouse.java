package com.careem.hacathon.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Table(name="warehouse")
@Entity
public class Warehouse {
private Long wareHouseId;
private String location;
private List<Booking> bookingId;

@javax.persistence.Id  
@GeneratedValue(strategy=GenerationType.AUTO)  
@Column(name = "warehouse_id", unique = true, nullable = false)
public Long getWareHouseId() {
	return wareHouseId;
}

public void setWareHouseId(Long wareHouseId) {
	this.wareHouseId = wareHouseId;
} 
public String getLocation() {
	return location;
}



public void setLocation(String location) {
	this.location = location;
}
@OneToMany(fetch = FetchType.LAZY, mappedBy = "booking")
public List<Booking> getBookingId() {
	return bookingId;
}

public void setBookingId(List<Booking> bookingId) {
	this.bookingId = bookingId;
}  


}
