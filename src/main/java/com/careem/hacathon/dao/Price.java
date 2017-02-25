package com.careem.hacathon.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Entity
@Table(name = "PRICE")
@Data
public class Price {
        @Id
        @Column(name = "ID", nullable = false)
        private int id;


}



