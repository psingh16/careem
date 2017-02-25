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
		
	}

	private static void loadItems(List<Booking> items) {
		// TODO Auto-generated method stub
		
	}
	
	

}
