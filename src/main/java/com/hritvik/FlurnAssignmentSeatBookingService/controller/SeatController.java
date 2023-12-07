package com.hritvik.FlurnAssignmentSeatBookingService.controller;

import com.hritvik.FlurnAssignmentSeatBookingService.model.Seat;

import com.hritvik.FlurnAssignmentSeatBookingService.model.SeatPricing;

import com.hritvik.FlurnAssignmentSeatBookingService.model.dto.SeatDetails;
import com.hritvik.FlurnAssignmentSeatBookingService.repository.SeatPricingRepository;
import com.hritvik.FlurnAssignmentSeatBookingService.repository.SeatRepository;
import com.hritvik.FlurnAssignmentSeatBookingService.service.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private  SeatService seatService;
    @Autowired
    private SeatPricingRepository seatPricingRepository;
    @Autowired
    private SeatRepository seatRepository;


    @GetMapping()
    public ResponseEntity<List<Seat>> getAllSeats() {
        List<Seat> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDetails> getSeatPricingById(@PathVariable Long id) {
       return seatService.getSeatPricingById(id);
    }
    @PostMapping("/addPrice")
    public void addPrice (@RequestBody List<SeatPricing> pricingList){
        seatPricingRepository.saveAll(pricingList);
    }
    @GetMapping("/get")
        public Optional<Seat> getSeat (@RequestParam Long id){
            return seatRepository.findById(id);
        }

}