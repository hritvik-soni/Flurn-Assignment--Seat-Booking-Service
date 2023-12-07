package com.hritvik.FlurnAssignmentSeatBookingService.service;


import com.hritvik.FlurnAssignmentSeatBookingService.model.Seat;
import com.hritvik.FlurnAssignmentSeatBookingService.model.dto.SeatDetails;
import com.hritvik.FlurnAssignmentSeatBookingService.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatPricingService seatPricingService;

    public List<Seat> getAllSeats() {
        return seatRepository.findAllByOrderBySeatClassAsc();
    }

    public Optional<Seat> getSeatById(Long seatId) {
        return seatRepository.findById(seatId);
    }

    public boolean isSeatBooked(Long seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        return seat.map(Seat::isBooked).orElse(false);
    }

    public List<Seat> bookSeats(List<Long> seatIds) {
        List<Seat> bookedSeats = new ArrayList<>();

        for (Long seatId : seatIds) {
            Optional<Seat> seatOptional = seatRepository.findById(seatId);

            if (seatOptional.isPresent() && !seatOptional.get().isBooked()) {
                Seat seat = seatOptional.get();
                seat.setBooked(true);
                seatRepository.save(seat);
                bookedSeats.add(seat);
            }
        }

        return bookedSeats;
    }

    public ResponseEntity<SeatDetails> getSeatPricingById(Long id) {

        Optional<Seat> seat = seatRepository.findById(id);
        SeatDetails seatDetails= new SeatDetails();

        if(seat.isPresent()){

            String seatClass = seat.get().getSeatClass();
            int totalSeatsInClass = getAllSeats().stream().filter(s -> s.getSeatClass().equals(seatClass)).toArray().length;
            int bookedSeats = (int) getAllSeats().stream().filter(Seat::isBooked).count();
            int percentageBooked = (bookedSeats * 100) / totalSeatsInClass;
            double amount = seatPricingService.calculateBookingAmount(seatClass, percentageBooked);

            seatDetails.setSeatClass(seat.get().getSeatClass());
            seatDetails.setId(seat.get().getId());
            seatDetails.setSeatPrice(amount);
            seatDetails.setSeatsBooked(seat.get().isBooked());
            return new ResponseEntity<>(seatDetails,HttpStatus.OK);

        }
        return new ResponseEntity<>(seatDetails, HttpStatus.BAD_REQUEST);
    }
}
