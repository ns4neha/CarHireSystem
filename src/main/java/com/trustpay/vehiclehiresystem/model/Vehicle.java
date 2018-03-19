package com.trustpay.vehiclehiresystem.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Integer id;

    @NotNull
    private VehicleType vehicleType;

    private String vehicleRegNbr;
    private String make;
    private String model;
    private Date yearOfPurchase;
    private int milesDone;

    @NotNull
    private Integer numberOfWheels;

    @NotNull
    private Integer passengersAllowed;

    private boolean booked = false;
}
