package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/employee", produces = APPLICATION_JSON_VALUE)
    public List<String> getEmployee() throws RestClientException, IOException {

        List<ServiceInstance> instances = discoveryClient.getInstances("employee-producer");
        ServiceInstance serviceInstance = instances.get(0);

        String baseUrl = serviceInstance.getUri().toString();
        baseUrl += "/employee";

        List<String> employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = null;
            try {
                response = restTemplate.exchange(baseUrl,
                        HttpMethod.GET, getHeaders(), String.class);
            } catch (Exception ex) {
                System.out.println(ex);
            }

            employees.add(response.getBody());
        }

        return employees;
    }

    private static HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}