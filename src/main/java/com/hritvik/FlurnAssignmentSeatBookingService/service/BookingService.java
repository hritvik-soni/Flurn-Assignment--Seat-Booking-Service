package com.hritvik.FlurnAssignmentSeatBookingService.service;

import com.hritvik.FlurnAssignmentSeatBookingService.model.Booking;
import com.hritvik.FlurnAssignmentSeatBookingService.model.Seat;
import com.hritvik.FlurnAssignmentSeatBookingService.repository.BookingRepository;
import com.hritvik.FlurnAssignmentSeatBookingService.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatPricingService seatPricingService;

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity<String> createBooking(List<Long> seatIds, String userName, String phoneNumber) {

        if(seatIds.isEmpty()){
            return new ResponseEntity<>("Seat Ids is needed to book the seats ",HttpStatus.BAD_REQUEST);
        }
        // Check if any of the selected seats are already booked
        for (Long seatId : seatIds) {
            if (seatService.isSeatBooked(seatId)) {
                return new ResponseEntity<>("Seat with ID " + seatId + " is already booked.",HttpStatus.BAD_REQUEST);
            }
        }

        // Book the selected seats
        List<Seat> bookedSeats = seatService.bookSeats(seatIds);

        // Calculate total amount based on pricing rules
        String seatClass = bookedSeats.get(0).getSeatClass(); // Assuming all seats belong to the same class
        int totalSeatsInClass = seatService.getAllSeats().stream().filter(s -> s.getSeatClass().equals(seatClass)).toArray().length;
        int percentageBooked = (bookedSeats.size() * 100) / totalSeatsInClass;
        double totalAmount = seatPricingService.calculateBookingAmount(seatClass, percentageBooked) * bookedSeats.size();

        // Create a booking
        Booking booking = new Booking();
        booking.setSeats(bookedSeats);
        booking.setUserName(userName);
        booking.setPhoneNumber(phoneNumber);
        booking.setTotalAmount(totalAmount);

        Booking savedBooking = bookingRepository.save(booking);

        // Return booking details
        return new ResponseEntity<>("Seats Booked Successfully " + "Booking Id : "+savedBooking.getId() +" and Total Amount : "+ totalAmount, HttpStatus.OK);
    }

    public List<Booking> getBookingsByUserIdentifier(String userIdentifier) {
        if (userIdentifier == null || userIdentifier.isEmpty()) {
            throw new IllegalArgumentException("User identifier cannot be empty.");
        }

        return bookingRepository.findByUserNameOrPhoneNumber(userIdentifier, userIdentifier);
    }
}
