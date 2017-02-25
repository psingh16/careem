package com.careem.hacathon.dao.model;

import com.careem.hacathon.pojos.GoodsCategory;
import com.careem.hacathon.pojos.GoodsType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="BOOKING")
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "EMAIL_ID", nullable = false)
	private String emailId;

	@Column(name = "BUSINESS_ID", nullable = false)
	private int businessId;

	@Column(name = "QUOTATION_ID", nullable = false)
	private int quotationId;

	@Column(name = "GOODS_TYPE", nullable = false)
	private GoodsType goodsType;

	@Column(name = "GOODS_CATEGORY", nullable = false)
	private GoodsCategory goodsCategory;

	@Column(name = "TOTAL_UNIT", nullable = false)
	private int totalUnits;

	@Column(name = "PRICE", nullable = false)
	private double price;

	@Column(name = "SOURCE", nullable = false)
	private String source;

	@Column(name = "DESTINATION", nullable = false)
	private String destination;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "BOOKING_ID")
	private List<Warehouse> warehouses = new ArrayList<Warehouse>();

	@Column(name = "CURRENT_STATE", nullable = false)
	private String currentState;

	@Column(name = "UPDATED_AT", nullable = false)
	private Date updatedAt;

	@Column(name = "CREATED_AT", nullable = false)
	private Date createdAt;
}
