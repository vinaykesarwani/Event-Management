package com.example.event_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueDto {

    private String name;

    private String address;

    private int capacity;

    private String contactNumber;

    private String email;
}
