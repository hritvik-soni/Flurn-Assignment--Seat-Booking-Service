package com.hritvik.FlurnAssignmentSeatBookingService.controller;


import com.hritvik.FlurnAssignmentSeatBookingService.model.Booking;
import com.hritvik.FlurnAssignmentSeatBookingService.model.dto.BookingRequest;
import com.hritvik.FlurnAssignmentSeatBookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @PostMapping("booking")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        List<Long> seatIds = request.getSeatIds();
        String userName = request.getUserName();
        String phoneNumber = request.getPhoneNumber();
        String email = request.getUserEmail();

        return bookingService.createBooking(seatIds, userName, phoneNumber,email);
    }

    @GetMapping("bookings")
    public ResponseEntity<?> getBookings(@RequestParam("userIdentifier") String userIdentifier) {
        List<Booking> bookings = bookingService.getBookingsByUserIdentifier(userIdentifier);
        return ResponseEntity.ok(bookings);
    }
}
