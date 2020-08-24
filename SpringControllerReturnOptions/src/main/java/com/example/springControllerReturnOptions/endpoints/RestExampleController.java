package com.example.springControllerReturnOptions.endpoints;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Synonymous with {@link org.springframework.stereotype.Controller}
 * and {@link org.springframework.web.bind.annotation.ResponseBody}
 *
 * The {@link org.springframework.web.bind.annotation.ResponseBody} puts the return of the method in the
 * response body of the response. However, we still need to specify the content type and the returned value
 * must be an acceptable object for that content type.
 */
@RestController
public class RestExampleController {

    @GetMapping(value = "/rest/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> json() {
        return Collections.singletonMap("List", Collections.singletonList("This is my rest response"));
    }

    @GetMapping(value = "/rest/text", produces = MediaType.TEXT_PLAIN_VALUE)
    public String text() {
        return "This is my rest text response";
    }

}
