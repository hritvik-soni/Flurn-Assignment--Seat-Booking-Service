package com.hritvik.FlurnAssignmentSeatBookingService.repository;


import com.hritvik.FlurnAssignmentSeatBookingService.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
//    List<Seat> findAllSeatsOrderedBySeatClass(String seatClass);

    List<Seat> findAllByOrderBySeatClassAsc();
}
