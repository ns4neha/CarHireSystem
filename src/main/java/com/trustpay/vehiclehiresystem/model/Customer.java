package com.trustpay.vehiclehiresystem.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class Customer {

    String emailAddress;
}
