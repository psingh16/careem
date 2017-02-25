package com.careem.hacathon.pojos;

import lombok.Data;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Data
public class GetQuotationRequestDTO {
    private int businessId;
    private String goodsType;
    private int noOfUnits;
    private double weight;
    private String goodsCategory;
    private String deliveryType;
}
