package com.hritvik.FlurnAssignmentSeatBookingService.repository;

import com.hritvik.FlurnAssignmentSeatBookingService.model.SeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatPricingRepository extends JpaRepository<SeatPricing,Long> {

    Optional<SeatPricing> findBySeatClass(String seatClass);
}
