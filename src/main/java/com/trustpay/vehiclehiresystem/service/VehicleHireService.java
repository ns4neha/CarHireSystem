package com.trustpay.vehiclehiresystem.service;

import com.trustpay.vehiclehiresystem.model.Booking;
import com.trustpay.vehiclehiresystem.model.Vehicle;
import com.trustpay.vehiclehiresystem.repository.VehicleBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleHireService {

    @Autowired
    VehicleBookingRepository vehicleBookingRepository;

    public Integer addNewVehicleToTheFleet(Vehicle vehicle) {

        return vehicleBookingRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(Boolean bookingStatus) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        if (bookingStatus == null) {
            vehicles = vehicleBookingRepository.findAll();
        } else if (bookingStatus) {
            vehicles = vehicleBookingRepository.findByBookedTrue();
        } else {
            vehicles = vehicleBookingRepository.findByBookedFalse();
        }
        return vehicles;
    }

    public boolean allocateVehicleToCustomer(Booking booking) {


        return vehicleBookingRepository.allocateVehiclesToCustomer(
                booking.getVehicle().stream().map(Vehicle::getId).collect(Collectors.toList()), booking.getCustomer());

    }
}

