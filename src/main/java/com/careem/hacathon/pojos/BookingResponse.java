package com.careem.hacathon.pojos;

import lombok.Data;
/*
 * @author Ranjan Kumar
 */
@Data
public class BookingResponse {

	private int bookingId;
	private String bookingStatus;
	private String message;
	
	public BookingResponse() {
		super();
	}

	public BookingResponse(int bookingId, String bookingStatus, String message) {
		super();
		this.bookingId = bookingId;
		this.bookingStatus = bookingStatus;
		this.message = message;
	}
	
}
