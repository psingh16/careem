package com.careem.hacathon.biz.service;

import com.careem.hacathon.dao.model.Booking;
import com.careem.hacathon.dao.model.Warehouse;
import com.careem.hacathon.dao.repository.BookingJpaRepository;
import com.careem.hacathon.dao.repository.WareHouseRepository;
import com.careem.hacathon.pojos.BookingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kumari.singh on 26/02/17.
 */
@Service
public class DispatcherService {

    @Autowired
    RoutingAlgorithm routingAlgorithm;

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    EmailSender emailSender;

    @Autowired
    BookingJpaRepository bookingJpaRepository;
    /**
     * send dispatch to destination
     *
     * @param items
     */
    public void dispatchItems(List<Booking> items) {

        loadItems(items);
        notifySender(items);
    }

    private void notifySender(List<Booking> items) {
        // TODO Auto-generated method stub
        for (Booking booking : items) {
            emailSender.sendMailRequestQuote("ranjan.kumar@zapcg.com", booking.getEmailId(), "CAREEM::Item dispatched", "Your Item with Booking Id :" +
                    booking.getId() + "has been dispatched");
        }
    }

    private void loadItems(List<Booking> items) {
        // TODO Auto-generated method stub



        for(Booking booking: items) {
            routingAlgorithm.fastestRoute(booking.getSource(), booking.getDestination());

            Warehouse warehouse = wareHouseRepository.findByBookingId(booking.getId());
            warehouse.setActive(false);
            wareHouseRepository.saveAndFlush(warehouse);

            booking.setCurrentState(BookingState.DISPATCHED.name());
            bookingJpaRepository.saveAndFlush(booking);
        }

    }
}
