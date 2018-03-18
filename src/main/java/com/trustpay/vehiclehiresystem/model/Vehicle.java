package com.trustpay.vehiclehiresystem.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class Vehicle {

    private Long id;
    private String vehicleType;

    private Integer numberOfWheels;

    private Integer passengers;

    private Boolean booked;
}
