package com.careem.hacathon.biz.service;

import java.util.Map;

import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import com.careem.hacathon.pojos.GoodsCategory;

public class PriceCalculator {
	/**@author Raju K Singh
	 * method calculates the pricing for a particular shipment delivery
	 * @param quotationDTO
	 * @return
	 */
	private static Map<GoodsCategory,Double>itemCostType;
	static
	{
		for(GoodsCategory category:GoodsCategory.values())
			
		{
			if(category.equals("solid"))
			itemCostType.put(category, new Double(10.0));
			else if(category.equals("fragile"))
				itemCostType.put(category, new Double(15.0));
			else if(category.equals("liquid"))
				itemCostType.put(category, new Double(13.0));
			else if(category.equals("inflammable"))
				itemCostType.put(category, new Double(20.0));
			
		}
	}
	public double getPrice(GetQuotationRequestDTO quotationDTO)
	{
		
		double basicCost=calculateBaisCost(quotationDTO);
		double transportationCost =calculateTransportCost(quotationDTO);
		double specialDeliveryCost =calculateSpecialCost(quotationDTO);
		double peakSeasonCost =calculatePeakSeasonCost(quotationDTO);
		double discount = calculateDiscount(quotationDTO);
		double costBeforeProfit= (basicCost+transportationCost+specialDeliveryCost+peakSeasonCost-discount);
		return costBeforeProfit+(costBeforeProfit*10/100);//10% profit
		
	}

	private double calculateDiscount(GetQuotationRequestDTO quotationDTO) {
		//calculate discount here such as loyality discount or bulk booking discount
		return 0;
	}

	private double calculatePeakSeasonCost(GetQuotationRequestDTO quotationDTO) {
		//calculate peak season or hours cost
		return 0;
	}

	private double calculateSpecialCost(GetQuotationRequestDTO quotationDTO) {
		// calculate the cost for express delivery of specific date or time delivery
		double specialServicePrice = 0.0;
		if(quotationDTO.getDeliveryType().equals("express"))
		{
			specialServicePrice= 100.0;
		}
		return specialServicePrice;
	}

	private double calculateTransportCost(GetQuotationRequestDTO quotationDTO) {
		// TODO Auto-generated method stub
		RoutingAlgorithm routeAlgo =new RoutingAlgorithm("A", "B");
		String route=null;
		if(quotationDTO.getDeliveryType().equals("express"))
			//get fastest route
		{
			
			 route=routeAlgo.fastestRoute();
		}
		else
			route =routeAlgo.shortestRoute();
		double cost= getLeastTransportCost(route);
		return cost;
	}

	private double getLeastTransportCost(String route) {
		// TODO Auto-generated method stub
		//retrieve data from DB for this specific route, least cost 
		//can be found among various available transport.
		return 0;
	}

	private double calculateBaisCost(GetQuotationRequestDTO quotationDTO) {
		// TODO Auto-generated method stub
		
		return quotationDTO.getWeight()*itemCostType.get(quotationDTO.getGoodsCategory()).doubleValue();
	}

}
