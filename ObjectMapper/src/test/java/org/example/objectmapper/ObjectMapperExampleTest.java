package org.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objectmapper.dto.NestedBodyInner;
import org.example.objectmapper.dto.MapObject;
import org.example.objectmapper.dto.NestedBody;
import org.example.objectmapper.dto.NestedInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ObjectMapperExampleTest {

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    public void mapStringAndStringToJsonExample() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("name", "Vasil");
        map.put("age", "15");

        String actualJson = objectMapper
                .writeValueAsString(map);

        String expectedJson = "{\"name\":\"Vasil\",\"key\":\"value\",\"age\":\"15\"}";

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void mapToObjectExample() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        map.put("name", "Vasil");
        map.put("age", 15);

        MapObject expected = new MapObject("value", "Vasil", 15);

        MapObject actual = objectMapper.convertValue(map, MapObject.class);

        assertEquals(expected, actual);
    }

    @Test
    public void nestedJsonToMapStringObjectToObjectExample() throws IOException {
        // Using Map<String, Object> allows me to have nested structures in the JSON.
        Map<String, Object> inner = new HashMap<>();
        inner.put("innerInfo", 26);
        Map<String, Object> properties = new HashMap<>();
        properties.put("nestedId", "nested-id");
        properties.put("nestedSize", 15);
        properties.put("inner", inner);
        NestedInfo expectedNestedInfo = new NestedInfo("id", "data", properties);
        String nestedJsonObject = objectMapper.writeValueAsString(expectedNestedInfo);
        NestedBody expectedBody = new NestedBody("nested-id", 15, new NestedBodyInner(26));

        NestedInfo actualNestedInfo = objectMapper.readerFor(NestedInfo.class).readValue(nestedJsonObject);
        NestedBody actualBody = objectMapper.convertValue(actualNestedInfo.getBody(), NestedBody.class);

        assertEquals(expectedNestedInfo, actualNestedInfo);
        assertEquals(expectedBody, actualBody);
    }


    @Test
    public void nestedJsonToMapStringObjectWithJsonAnySetterExample() throws IOException {

    }
}