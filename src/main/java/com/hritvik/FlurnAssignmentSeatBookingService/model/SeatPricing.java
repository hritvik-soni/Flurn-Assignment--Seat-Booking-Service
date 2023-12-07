package com.hritvik.FlurnAssignmentSeatBookingService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatPricing {

    @Id
    private Long id;
    private String seatClass;
    private double minPrice;
    private double maxPrice;
    private double normalPrice;

}
