package com.trustpay.vehiclehiresystem.controller;

import com.trustpay.vehiclehiresystem.model.Vehicle;
import com.trustpay.vehiclehiresystem.service.VehicleHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class VehicleHireController {

    @Autowired
    VehicleHireService vehicleHireService;

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public ResponseEntity addVehicleToFleet(@RequestBody Vehicle newVehicle) {

        return new ResponseEntity<Object>(vehicleHireService.addNewVehicleToTheFleet(), HttpStatus.CREATED);
    }
}
