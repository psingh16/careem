package com.careem.hacathon.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    private String quotationId;

    @Column(name = "GOODS_TYPE", nullable = false)
    private String goodsType;

    @Column(name = "GOODS_CATEGORY", nullable = false)
    private String goodsCategory;

    @Column(name = "TYPE_OF_DELIVERY")
    private String typeOfDelivery;

    @Column(name = "TOTAL_UNITS", nullable = false)
    private int totalUnits;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "SOURCE", nullable = false)
    private String source;

    @Column(name = "DESTINATION", nullable = false)
    private String destination;

    @Column(name = "CURRENT_STATE", nullable = false)
    private String currentState;
	@Column(name = "UPDATED_AT", nullable = false)
	private Date updatedAt;

	@Column(name = "CREATED_AT", nullable = false)
	private Date createdAt;

}
