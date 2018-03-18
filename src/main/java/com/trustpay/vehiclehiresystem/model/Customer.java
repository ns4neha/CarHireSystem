package com.trustpay.vehiclehiresystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class Customer {

    String emailAddress;
}
