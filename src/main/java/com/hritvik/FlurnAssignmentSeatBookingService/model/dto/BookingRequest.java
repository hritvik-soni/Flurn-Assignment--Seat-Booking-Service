package com.hritvik.FlurnAssignmentSeatBookingService.model.dto;

import com.hritvik.FlurnAssignmentSeatBookingService.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    private List<Long> seatIds;
    private String userName;
    private String phoneNumber;
}
