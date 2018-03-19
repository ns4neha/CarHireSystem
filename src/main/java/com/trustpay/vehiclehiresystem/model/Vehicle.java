package com.trustpay.vehiclehiresystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Vehicle {

    private Integer id;
    private VehicleType vehicleType;

    private String vehicleRegNbr;
    private String make;
    private String model;
    private Date yearOfPurchase;
    private int milesDone;

    private Integer numberOfWheels;
    private Integer passengersAllowed;

    private Boolean booked = false;
}
