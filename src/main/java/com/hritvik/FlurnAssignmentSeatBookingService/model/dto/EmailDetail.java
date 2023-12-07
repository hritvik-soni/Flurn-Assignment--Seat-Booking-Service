package com.hritvik.FlurnAssignmentSeatBookingService.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetail {
    private String recipient;
    private String messageBody;
    private String subject;

}