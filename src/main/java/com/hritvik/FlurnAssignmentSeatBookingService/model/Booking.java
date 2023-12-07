package com.hritvik.FlurnAssignmentSeatBookingService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "seat_id")
    private List<Seat> seats;

    private String userName;
    private String phoneNumber;
    private double totalAmount;

}
