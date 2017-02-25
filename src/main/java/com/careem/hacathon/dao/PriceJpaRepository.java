package com.careem.hacathon.dao;

import com.careem.hacathon.dao.model.Price;
import com.careem.hacathon.pojos.GoodsCategory;
import com.careem.hacathon.pojos.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<Price, Integer> {
    Price findByGoodsTypeAndGoodsCategory(GoodsType goodsType, GoodsCategory goodsCategory);
}
