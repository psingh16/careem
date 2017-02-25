package com.careem.hacathon.dao.repository;

import com.careem.hacathon.dao.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by kumari.singh on 26/02/17.
 */
public interface WareHouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query("select m from Warehouse m where m.active=true")
    List<Warehouse> findActiveGoods();

    Warehouse findByBookingId(int bookingId);
}
