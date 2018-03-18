package com.trustpay.vehiclehiresystem.service;

import com.trustpay.vehiclehiresystem.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleHireService {

    public Vehicle addNewVehicleToTheFleet() {
        return new Vehicle();
    }
}
