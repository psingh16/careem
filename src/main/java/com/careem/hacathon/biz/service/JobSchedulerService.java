package com.careem.hacathon.biz.service;

import com.careem.hacathon.dao.model.Booking;
import com.careem.hacathon.dao.model.Warehouse;
import com.careem.hacathon.dao.repository.BookingJpaRepository;
import com.careem.hacathon.dao.repository.WareHouseRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kumari.singh on 26/02/17.
 */
@Service
public class JobSchedulerService {

    @Autowired
    private DispatcherService dispatcherService;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private BookingJpaRepository bookingJpaRepository;

    //@Scheduled(cron = "0 0 12 * * ?")
    @Scheduled(fixedDelay = 5 * 60 * 1000, initialDelay = 1 * 60 * 1000)
    public void onSchedule() {
        List<Warehouse> warehouses = wareHouseRepository.findActiveGoods();
        List<Booking> bookingList = Lists.newArrayList();

        for(Warehouse warehouse: warehouses) {
            Booking booking = bookingJpaRepository.findOne(warehouse.getBookingId());
            bookingList.add(booking);

        }
        dispatcherService.dispatchItems(bookingList);
    }

}
