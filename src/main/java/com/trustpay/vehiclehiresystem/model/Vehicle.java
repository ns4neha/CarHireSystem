package com.trustpay.vehiclehiresystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vehicleType;

    private Integer numberOfWheels;

    private Integer passengers;

    private Boolean booked;
}
