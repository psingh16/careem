package com.careem.hacathon.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="QUOTATION_DETAILS")
public class QuotationDetails {
private Long id;
private Goods goods;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)  
@Column(name = "quotation_id", unique = true, nullable = false)
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@OneToOne(mappedBy="quotationDetails")
public Goods getGoods() {
	return goods;
}
public void setGoods(Goods goods) {
	this.goods = goods;
}


}
