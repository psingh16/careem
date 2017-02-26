package com.careem.hacathon.dao.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by kumari.singh on 26/02/17.
 */
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

    @Column(name = "TYPE_OF_DELIVERY")
    private String typeOfDelivery;

    @Column(name = "ACTIVE")
    private boolean active;

}

