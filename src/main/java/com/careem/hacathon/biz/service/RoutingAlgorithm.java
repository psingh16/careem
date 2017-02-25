package com.careem.hacathon.biz.service;

public class RoutingAlgorithm {
	
	private String source;
	private String destination;
	public RoutingAlgorithm(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	/**@author Raju K Singh
	 * return fastest possible route between source and destination
	 * @return
	 */
	public String fastestRoute()
	{
		//get all possible routes between given source and destination
		/**
		 * for example if Source is A and destination is B
		 * below may be possible routes :
		 * A ->B  20min
		 * A->C->B 18min
		 * A->C->D->B 21min
		 * 
		 * above information will be stored in DB
		 */
		fetchRoutes(this.source,this.destination);
		return "A C B";
		
	}
	private String fetchRoutes(String source2, String destination2) {
		// TODO Auto-generated method stub
		//make a DB call 
		return null;
	}
	public String shortestRoute()
	{
		//get all possible routes between given source and destination
				/**
				 * for example if Source is A and destination is B
				 * below may be possible routes :
				 * A ->B  20km
				 * A->C->B 18km
				 * A->C->D->B 21km
				 * 
				 * above information will be stored in DB
				 */
		fetchRoutes(this.source,this.destination);

		return "A C B";
	}
}
