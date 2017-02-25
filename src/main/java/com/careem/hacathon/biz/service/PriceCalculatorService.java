package com.careem.hacathon.biz.service;

import com.careem.hacathon.dao.model.Price;
import com.careem.hacathon.dao.repository.PriceJpaRepository;
import com.careem.hacathon.pojos.DeliveryType;
import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import com.careem.hacathon.pojos.GoodsCategory;
import com.careem.hacathon.pojos.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kumari.singh on 26/02/17.
 */
@Service
public class PriceCalculatorService {

    @Autowired
    PriceJpaRepository priceJpaRepository;

    /**@author Raju K Singh
     * method calculates the pricing for a particular shipment delivery
     * @param quotationDTO
     * @return
     */

    public double getPrice(GetQuotationRequestDTO quotationDTO)   {
        double basicCost= getBasicCost(quotationDTO);
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
        if(quotationDTO.getDeliveryType().equals(DeliveryType.express.name()))
        {
            specialServicePrice= 100.0;
        }
        return specialServicePrice;
    }

    private double calculateTransportCost(GetQuotationRequestDTO quotationDTO) {
        // TODO Auto-generated method stub
        RoutingAlgorithm routeAlgo =new RoutingAlgorithm();
        String route=null;
        if(quotationDTO.getDeliveryType().equals("express"))
        //get fastest route
        {

            route=routeAlgo.fastestRoute("A", "B");
        }
        else
            route =routeAlgo.shortestRoute("A", "B");
        double cost= getLeastTransportCost(route);
        return cost;
    }

    private double getLeastTransportCost(String route) {
        // TODO Auto-generated method stub
        //retrieve data from DB for this specific route, least cost
        //can be found among various available transport.
        return 0;
    }

    private double getBasicCost(GetQuotationRequestDTO getQuotationRequestDTO) {
        GoodsCategory goodsCategory = GoodsCategory.valueOf(getQuotationRequestDTO.getGoodsCategory());
        GoodsType goodsType = GoodsType.valueOf(getQuotationRequestDTO.getGoodsType());

        Price price = priceJpaRepository.findByGoodsTypeAndGoodsCategory(goodsType, goodsCategory);
        return price.getPrice() * getQuotationRequestDTO.getNoOfUnits();
    }



}

