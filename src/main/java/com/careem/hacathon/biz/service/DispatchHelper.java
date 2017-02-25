package com.careem.hacathon.biz.service;

import java.util.List;

import com.careem.hacathon.dao.model.Booking;

public class DispatchHelper {
	/**
	 * send dispatch to destination
	 * @param items
	 */
	public static void dispatchItems(List<Booking> items)
	{
		
		loadItems(items);
		notifySender(items);
	}

	private static void notifySender(List<Booking> items) {
		// TODO Auto-generated method stub
		EmailSender emailHelper=new EmailSender();
		for(Booking booking:items)
		{
			emailHelper.sendMailRequestQuote("team@careem.com", booking.getEmailId(), "CAREEM::Item dispatched", "Your Item with Booking Id :"+
				booking.getId()+"has been dispatched");
		}
	}

	private static void loadItems(List<Booking> items) {
		// TODO Auto-generated method stub
		//dispatch items for delivery
		
	}
	
	

}
