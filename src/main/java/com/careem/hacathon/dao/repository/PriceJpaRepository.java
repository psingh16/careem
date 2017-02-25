package com.careem.hacathon.dao.repository;

import com.careem.hacathon.dao.model.Price;
import com.careem.hacathon.pojos.GoodsCategory;
import com.careem.hacathon.pojos.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kumari.singh on 26/02/17.
 */
public interface PriceJpaRepository extends JpaRepository<Price, Integer> {
    Price findByGoodsTypeAndGoodsCategory(GoodsType goodsType, GoodsCategory goodsCategory);
}

