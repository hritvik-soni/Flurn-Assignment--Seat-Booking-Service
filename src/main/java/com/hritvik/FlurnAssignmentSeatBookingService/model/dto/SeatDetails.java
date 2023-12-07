package com.hritvik.FlurnAssignmentSeatBookingService.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDetails {

    private Long id;
    private String seatClass;
    private double seatPrice;
//    private boolean seatsBooked;

}
