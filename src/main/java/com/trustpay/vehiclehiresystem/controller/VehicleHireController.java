package com.trustpay.vehiclehiresystem.controller;

import com.trustpay.vehiclehiresystem.model.Vehicle;
import com.trustpay.vehiclehiresystem.service.VehicleHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
public class VehicleHireController {

    @Autowired
    VehicleHireService vehicleHireService;

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public ResponseEntity addVehicleToFleet(@RequestBody Vehicle newVehicle) {

        vehicleHireService.addNewVehicleToTheFleet();
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getVehicleInfo",
            method = RequestMethod.GET,
            params = {"bookingStatus"})
    @ResponseBody
    public List<Vehicle> getVehicleInfo(@RequestParam("bookingStatus") Boolean bookingStatus) {

        return vehicleHireService.getAllVehicles();
    }

    @RequestMapping(value = "/allocateVehicle", method = RequestMethod.POST)
    public ResponseEntity allocateVehicleToCustomer(@RequestBody Vehicle newVehicle) {

        vehicleHireService.allocateVehicleToCustomer();
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
}
