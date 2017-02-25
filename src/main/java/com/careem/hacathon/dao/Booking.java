package com.careem.hacathon.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Booking")
public class Booking {
	private Long bookingId;
	private Warehouse wareHouse;
	private String status;
	private String bookedBy;
	private Date bookingDate;
	private String source;
	private String destination;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "booking_id", unique = true, nullable = false)
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	@ManyToOne
	@JoinColumn(name="warehouse_id")
	public Warehouse getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookedBy() {
		return bookedBy;
	}
	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

}
