package com.careem.hacathon.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careem.hacathon.pojos.BookingResponse;
import com.careem.hacathon.biz.service.BookingService;

import lombok.extern.slf4j.Slf4j;

/*
 * @author Ranjan Kumar
 */

@RequestMapping("/v1")
@Slf4j
@RestController
public class BookingStatusResource {

	@Autowired
	private BookingService bookingService;
	@RequestMapping(value="/booking/{bookingId}/status",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse getBookingDetail(@PathVariable("bookingId") int bookingId) throws Exception {

		BookingResponse bookingResponse = bookingService.status(bookingId);
		
		return bookingResponse;

	}
}
