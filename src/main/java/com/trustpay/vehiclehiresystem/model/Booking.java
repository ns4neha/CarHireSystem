package com.trustpay.vehiclehiresystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NotNull
public class Booking {

    Customer customer;
    List<Vehicle> vehicle;
}
