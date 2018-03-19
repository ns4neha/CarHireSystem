package com.trustpay.vehiclehiresystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Vehicle {

    private Long id;
    private VehicleType vehicleType;

    private Integer numberOfWheels;
    private Integer passengersAllowed;

    private Boolean booked;
}
