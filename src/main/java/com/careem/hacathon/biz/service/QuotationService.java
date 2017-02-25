package com.careem.hacathon.biz.service;

import com.careem.hacathon.dao.GenericDatabaseService;
import com.careem.hacathon.dao.model.Booking;
import com.careem.hacathon.dao.model.Price;
import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import com.careem.hacathon.pojos.GoodsCategory;
import com.careem.hacathon.pojos.GoodsType;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Slf4j
public class QuotationService {

    @Autowired
    private GenericDatabaseService genericDatabaseService;

    @Autowired
    private EmailSender emailSender;

    public void updateBooking(GetQuotationRequestDTO getQuotationRequestDTO) {
        //find price
        GoodsCategory goodsCategory = GoodsCategory.valueOf(getQuotationRequestDTO.getGoodsCategory());
        GoodsType goodsType = GoodsType.valueOf(getQuotationRequestDTO.getGoodsType());
        Map<String, Object> param = Maps.newHashMap();
        param.put("goodsType", goodsType);
        param.put("goodsCategory", goodsCategory);
        List<Price> prices = genericDatabaseService.findUsingQuery("Price.findByGoodsTypeAndGoodsCategory", param);

        Price price = prices.get(0);

        Booking booking = new Booking();
        booking.setBusinessId(getQuotationRequestDTO.getBusinessId());
        booking.setCurrentState("created");
        booking.setGoodsCategory(GoodsCategory.valueOf(getQuotationRequestDTO.getGoodsCategory()));
        booking.setGoodsType(GoodsType.valueOf(getQuotationRequestDTO.getGoodsType()));
        booking.setEmailId(getQuotationRequestDTO.getEmailId());
        booking.setPrice(price.getPrice());
        booking.setEmailId(getQuotationRequestDTO.getEmailId());
        booking.setTotalUnits(getQuotationRequestDTO.getNoOfUnits());
        booking.setSource(getQuotationRequestDTO.getFromAddress().getCity());
        booking.setDestination(getQuotationRequestDTO.getToAddress().getCity());
        genericDatabaseService.create(booking);

        emailSender.sendMailRequestQuote("team@careem.com", booking.getEmailId(), "Regarding Quotation: "+booking.getQuotationId(),
                "Your quotation for goods is Rs. "+booking.getPrice());
    }
}
