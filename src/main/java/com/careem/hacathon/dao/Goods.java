package com.careem.hacathon.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Goods")
public class Goods {
	private Long id;
	private QuotationDetails quotation;
	@OneToOne
	@JoinColumn(name="quotation_id")
	public QuotationDetails getQuotation() {
		return quotation;
	}
	public void setQuotation(QuotationDetails quotation) {
		this.quotation = quotation;
	}
	/**
	 * type of goods
	 */
	
private String type;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)  
@Column(name = "goods_id", unique = true, nullable = false)
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
/**
 * category of goods like fragile , inflammable
 */
private Category category;
/**
 * no of unit
 */
private int noOfUnit;
/**
 * measurement unit
 */
private String wtUnit;
/**
 * weight
 */
private double weight;
/**
 * volume unit
 */
private String volUnit;
/**
 * volume
 */
private double volume;
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public int getNoOfUnit() {
	return noOfUnit;
}
public void setNoOfUnit(int noOfUnit) {
	this.noOfUnit = noOfUnit;
}
public String getWtUnit() {
	return wtUnit;
}
public void setWtUnit(String wtUnit) {
	this.wtUnit = wtUnit;
}
public double getWeight() {
	return weight;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public String getVolUnit() {
	return volUnit;
}
public void setVolUnit(String volUnit) {
	this.volUnit = volUnit;
}
public double getVolume() {
	return volume;
}
public void setVolume(double volume) {
	this.volume = volume;
}
@Override
public String toString() {
	return "Goods [type=" + type + ", category=" + category + ", noOfUnit=" + noOfUnit + ", wtUnit=" + wtUnit
			+ ", weight=" + weight + ", volUnit=" + volUnit + ", volume=" + volume + "]";
}

}
