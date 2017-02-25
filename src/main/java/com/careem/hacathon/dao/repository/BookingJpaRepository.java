package com.careem.hacathon.dao.repository;

import com.careem.hacathon.dao.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kumari.singh on 26/02/17.
 */
public interface BookingJpaRepository extends JpaRepository<Booking, Integer> {
    Booking findByQuotationId(String quotationId);
}