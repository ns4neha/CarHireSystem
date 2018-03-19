package com.trustpay.vehiclehiresystem.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class Booking {

    Customer customer;
    List<Vehicle> vehicle;
}
