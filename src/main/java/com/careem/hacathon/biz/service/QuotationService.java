package com.careem.hacathon.biz.service;

import com.careem.hacathon.dao.model.Booking;
import com.careem.hacathon.dao.repository.BookingJpaRepository;
import com.careem.hacathon.pojos.BookingState;
import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kumari.singh on 26/02/17.
 */
@Service
public class QuotationService {

    @Autowired
    private BookingJpaRepository bookingJpaRepository;

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    private EmailSender emailSender;

    public void updateBooking(GetQuotationRequestDTO getQuotationRequestDTO) {
        //find price
        double price = priceCalculatorService.getPrice(getQuotationRequestDTO);

        Booking booking = new Booking();
        booking.setBusinessId(getQuotationRequestDTO.getBusinessId());
        booking.setCurrentState(BookingState.CREATED.name());
        booking.setGoodsCategory(getQuotationRequestDTO.getGoodsCategory());
        booking.setGoodsType(getQuotationRequestDTO.getGoodsType());
        booking.setPrice(price);
        booking.setEmailId(getQuotationRequestDTO.getEmailId());
        booking.setTotalUnits(getQuotationRequestDTO.getNoOfUnits());
        booking.setSource(getQuotationRequestDTO.getFromAddress().getCity());
        booking.setDestination(getQuotationRequestDTO.getToAddress().getCity());
        booking.setQuotationId(getQuotationRequestDTO.getQuotationId());
        booking.setTypeOfDelivery(getQuotationRequestDTO.getDeliveryType());
        bookingJpaRepository.saveAndFlush(booking);

        emailSender.sendMailRequestQuote("ranjan.kumar@zapcg.com", booking.getEmailId(), "Regarding Quotation: "+booking.getQuotationId(),
                "Your quotation for goods is Rs. "+booking.getPrice());
    }
}
