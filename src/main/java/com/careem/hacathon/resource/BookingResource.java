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
public class BookingResource {

	@Autowired
	BookingService bookingService;

	@RequestMapping(value="/booking/{quotationId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse getBookingDetail(@PathVariable("quotationId") String quotationId) throws Exception {

		BookingResponse bookingResponse = bookingService.book(quotationId);
		return bookingResponse;
	}
}
