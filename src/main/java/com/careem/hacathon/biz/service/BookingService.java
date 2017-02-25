package com.careem.hacathon.biz.service;


import com.careem.hacathon.dao.model.Booking;
import com.careem.hacathon.dao.model.Warehouse;
import com.careem.hacathon.dao.repository.BookingJpaRepository;
import com.careem.hacathon.dao.repository.WareHouseRepository;
import com.careem.hacathon.pojos.BookingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careem.hacathon.pojos.BookingResponse;

/*
 * @author Ranjan Kumar
 */
@Service
public class BookingService {
	@Autowired
	private BookingJpaRepository bookingJpaRepository;

	@Autowired
	private WareHouseRepository wareHouseRepository;

	
	public BookingResponse book(String quotationId) {
		Booking booking = bookingJpaRepository.findByQuotationId(quotationId);
		booking.setCurrentState(BookingState.CONFIRMED.name());
		bookingJpaRepository.saveAndFlush(booking);

		Warehouse warehouse = new Warehouse();
		warehouse.setBookingId(booking.getId());
		warehouse.setLocation(booking.getSource());
		warehouse.setActive(true);
		wareHouseRepository.saveAndFlush(warehouse);

		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setBookingId(booking.getId());
		bookingResponse.setBookingStatus(booking.getCurrentState());
		bookingResponse.setMessage("Thank you for booking!! You can check your booking status now using bookingId: "+bookingResponse.getBookingId());
		return bookingResponse;
	}

	public BookingResponse status(int bookingId) {
		Booking booking = bookingJpaRepository.findOne(bookingId);

		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setBookingId(booking.getId());
		bookingResponse.setBookingStatus(booking.getCurrentState());
		bookingResponse.setMessage("");
		return bookingResponse;
	}

}
