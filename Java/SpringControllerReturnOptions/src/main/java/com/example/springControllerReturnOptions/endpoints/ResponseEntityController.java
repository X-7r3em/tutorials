package com.example.springControllerReturnOptions.endpoints;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

/**
 * This is an example of what a Controller can return when no
 * {@link org.springframework.web.bind.annotation.ResponseBody} is present.
 *
 * This was inspired by {@link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController} as it
 * can return both a JSON or a HTML response depending on Accept of the client
 */
@Controller
public class ResponseEntityController {
    // Controller can return Response entities

    // Plain text example
    @GetMapping(value = "/text")
    public ResponseEntity<?> text() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>("This is plain text", headers, HttpStatus.ACCEPTED);
    }

    // Html Example
    @GetMapping(value = "/html")
    public ResponseEntity<?> html() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>("<h1>This is HTML<h1>", headers, HttpStatus.ACCEPTED);
    }

    // XML Example
    @GetMapping(value = "/xml")
    public ResponseEntity<?> xml() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new ResponseEntity<>(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator() +
                "<note>" + System.lineSeparator() +
                "    <body>This is XML body</body>" + System.lineSeparator() +
                "</note>", headers, HttpStatus.ACCEPTED);
    }

    // XML Example
    @GetMapping(value = "/json")
    public ResponseEntity<?> json() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(Collections.singletonMap("Key", "This is JSON"), headers, HttpStatus.ACCEPTED);
    }
}
