package com.example.objectmapper;

import com.example.objectmapper.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objectmapper.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        AnySetterNested anySetterNested = new AnySetterNested("nested-name", 15);
        AnySetterInfo anySetterInfo = new AnySetterInfo("info-id", "DDS", anySetterNested);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(anySetterInfo);
        Map<String, Object> inner = new LinkedHashMap<>();
        inner.put("name", "nested-name");
        inner.put("size", 15);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("brand", "DDS");
        body.put("nested", inner);
        AnySetterCollected expected = new AnySetterCollected("info-id", body);

        AnySetterCollected actual = objectMapper.readerFor(AnySetterCollected.class).readValue(json);

        assertEquals(expected, actual);
    }

    @Test
    public void convertNestedObjectToMapStringObjectExample() throws IOException {
        AnySetterNested anySetterNested = new AnySetterNested("nested-name", 15);
        AnySetterInfo anySetterInfo = new AnySetterInfo("info-id", "DDS", anySetterNested);
        Map<String, Object> inner = new LinkedHashMap<>();
        inner.put("name", "nested-name");
        inner.put("size", 15);
        Map<String, Object> expectedMap = new LinkedHashMap<>();
        expectedMap.put("infoId", "info-id");
        expectedMap.put("brand", "DDS");
        expectedMap.put("nested", inner);

        Map<String, Object> actualMap = objectMapper.convertValue(anySetterInfo, new TypeReference<Map<String, Object>>() {});

        assertEquals(expectedMap, actualMap);
    }
}