package com.example.exceptionhandling.services;

import com.example.exceptionhandling.annotations.CarViolation;
import com.example.exceptionhandling.dtos.Car;
import com.example.exceptionhandling.dtos.ProxyResponseContainer;
import com.example.exceptionhandling.exceptions.FailedProxyRequestException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Validated
public class CarServiceImpl implements CarService {
    @Override
    @CarViolation
    // @Valid here may produce an Constraint Exception for Liskov Substitution
    public Car createService(Car car) {
        return car;
    }

    @Override
    public Car createProxy(Car car) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Car> httpEntity = new HttpEntity<>(car, headers);
        ResponseEntity<ProxyResponseContainer>
                response =
                restTemplate.exchange(
                        "http://localhost:8080/carproxy",
                        HttpMethod.POST,
                        httpEntity,
                        ProxyResponseContainer.class);

        if (Objects.requireNonNull(response.getBody()).getStatus() != null) {
            throw new FailedProxyRequestException(response.getBody().toString());
        }

        return new Car(response.getBody().getMake(), response.getBody().getModel());
    }
}
