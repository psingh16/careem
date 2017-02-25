package com.careem.hacathon.dao.model;

import lombok.Data;

import javax.persistence.*;

@Table(name="WAREHOUSE")
@Entity
@Data
public class Warehouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "BOOKING_ID")
	private Integer bookingId;

}
