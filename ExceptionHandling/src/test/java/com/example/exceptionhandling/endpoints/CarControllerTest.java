package com.example.exceptionhandling.endpoints;

import com.example.exceptionhandling.dtos.ApiError;
import com.example.exceptionhandling.dtos.Car;
import com.example.exceptionhandling.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    @Test
    public void createCar_givenInvalidCar_willThrowMethodArgumentNotValidException() throws Exception {
        Car car = new Car();
        String message = "This error is due to an invalid Request body and MethodArgumentNotValidException";
        List<String> errors = Arrays.asList("make: Make must not be null","model: Model must not be null");
        ApiError expected =
                new ApiError(HttpStatus.BAD_REQUEST, message, errors);

        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void createCarService_givenInvalidCar_willThrowConstraintViolationException() throws Exception {
        Car car = new Car();
        String message = "Some violation message";
        ApiError expected =
                new ApiError(HttpStatus.BAD_REQUEST, message, new ArrayList<>());

        given(carService.createService(car))
                .willThrow(new ConstraintViolationException("Some violation message", new HashSet<>()));

        mockMvc.perform(post("/carservice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }
}