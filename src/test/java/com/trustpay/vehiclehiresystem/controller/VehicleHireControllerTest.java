package com.trustpay.vehiclehiresystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trustpay.vehiclehiresystem.model.Booking;
import com.trustpay.vehiclehiresystem.model.Customer;
import com.trustpay.vehiclehiresystem.model.Vehicle;
import com.trustpay.vehiclehiresystem.model.VehicleType;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by umesh.agarwal on 19/03/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleHireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_addVehicleToFleet_Successful() throws Exception {

        Vehicle carVehicle = Vehicle.builder().vehicleType(VehicleType.CAR).numberOfWheels(4).passengersAllowed(5).build();

        //WHEN add vehicle - to return success message and created id...
        this.mockMvc.perform(post("/vehicles/addVehicle").contentType(MediaType.APPLICATION_JSON).content(asJsonString(carVehicle)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{\"id\":")));
    }

    @Test
    public void getVehicleInfo() throws Exception {

        Vehicle carVehicle = Vehicle.builder().vehicleType(VehicleType.CAR).vehicleRegNbr("TEST1234").numberOfWheels(4).passengersAllowed(5).build();
        //GIVEN - vehicle exists
        ResultActions resultActions = this.mockMvc.perform(post("/vehicles/addVehicle").contentType(MediaType.APPLICATION_JSON).content(asJsonString(carVehicle)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{\"id\":")));

        //WHEN get vehicle info to return the vehicle details
        this.mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/getVehicleInfo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].vehicleRegNbr", is(carVehicle.getVehicleRegNbr())))
                .andExpect(jsonPath("$[0].numberOfWheels", is(carVehicle.getNumberOfWheels())));
    }

    @Test
    public void allocateVehicleToCustomer() throws Exception {

        Vehicle carVehicle = Vehicle.builder().vehicleType(VehicleType.CAR).vehicleRegNbr("TEST1234").numberOfWheels(4).passengersAllowed(5).build();

        //GIVEN - Create vehicle
        MvcResult result = this.mockMvc.perform(post("/vehicles/addVehicle").contentType(MediaType.APPLICATION_JSON).content(asJsonString(carVehicle)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{\"id\":"))).andReturn();

        JSONObject obj = new JSONObject(result.getResponse().getContentAsString());
        carVehicle.setId(obj.getInt("id"));

        Booking booking = Booking.builder().customer(Customer.builder().emailAddress("test@test.com").build()).vehicle(Lists.newArrayList(carVehicle)).build();

        //WHEN - allocate vehicle to customer
        this.mockMvc.perform(post("/vehicles/bookVehicle").contentType(MediaType.APPLICATION_JSON).content(asJsonString(booking)))
                .andDo(print())
                .andExpect(status().isCreated());

        //THEN - available for booking vehicle should not show this up
        this.mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/getVehicleInfo?isBooked=false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));

        //THEN - already bookd vehicle should show this up
        this.mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/getVehicleInfo?isBooked=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].vehicleRegNbr", is(carVehicle.getVehicleRegNbr())))
                .andExpect(jsonPath("$[0].numberOfWheels", is(carVehicle.getNumberOfWheels())));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}