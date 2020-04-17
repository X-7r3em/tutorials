package com.example.unittesting.services;

import com.example.unittesting.dtos.Echo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class RemoteServiceImpl implements RemoteService {

    private final RestTemplate restTemplate;

    public RemoteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getEchoMessage(String message) {
        return restTemplate.getForObject("/echo/?message={message}", String.class, message);
    }

    @Override
    public String postEchoMessage(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("test", "This is a test header");
        HttpEntity<Echo> requestEntity = new HttpEntity<>(new Echo(message), headers);
        Echo echo = restTemplate.exchange(
                "/echo/",
                HttpMethod.POST,
                requestEntity,
                Echo.class)
                .getBody();
        return Objects.requireNonNull(echo).getMessage();
    }

}
