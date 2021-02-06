package com.example.unittesting.endpoints;

import com.example.unittesting.dtos.Echo;
import com.example.unittesting.dtos.Odd;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RemoteController {
    @GetMapping(value = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEchoMessage(@RequestParam String message) {
        return "Echo " + message;
    }

    @PostMapping(value = "/echo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Echo postEchoMessage(@RequestBody Echo model) {
        return new Echo("Echo " + model.getMessage());
    }

    @GetMapping(value = "/odd", produces = MediaType.APPLICATION_JSON_VALUE)
    public Odd getOdd() {
        return new Odd("Some name", 12, 4214);
    }

    @PostMapping(value = "/odd",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Odd postOdd(@RequestBody Odd odd) {
        return new Odd("Mock " + odd.getName(), odd.getAmount() + 1, odd.getIndex() + 10);
    }
}
