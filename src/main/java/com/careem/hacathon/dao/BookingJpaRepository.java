package com.careem.hacathon.dao;

import com.careem.hacathon.dao.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Repository
public interface BookingJpaRepository extends JpaRepository<Booking, Integer> {
}
