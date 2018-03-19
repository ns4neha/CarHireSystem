package com.trustpay.vehiclehiresystem.controller;

import com.trustpay.vehiclehiresystem.model.Booking;
import com.trustpay.vehiclehiresystem.model.Vehicle;
import com.trustpay.vehiclehiresystem.service.VehicleHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleHireController {

    @Autowired
    VehicleHireService vehicleHireService;

    JsonParser jsonParser = JsonParserFactory.getJsonParser();

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public ResponseEntity<String> addVehicleToFleet(@RequestBody Vehicle newVehicle) {

        return new ResponseEntity<String>
                ("{\"id\": " + vehicleHireService.addNewVehicleToTheFleet(newVehicle) + "}"
                        , HttpStatus.CREATED);
    }

    @GetMapping(value = "/getVehicleInfo")
    @ResponseBody
    public List<Vehicle> getVehicleInfo(@RequestParam(value = "bookingStatus", required = false) Boolean bookingStatus) {

        return vehicleHireService.getAllVehicles(bookingStatus);
    }

    @RequestMapping(value = "/bookVehicle", method = RequestMethod.POST)
    public ResponseEntity allocateVehicleToCustomer(@RequestBody Booking vehicleBooking) {

        vehicleHireService.allocateVehicleToCustomer(vehicleBooking);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
}
