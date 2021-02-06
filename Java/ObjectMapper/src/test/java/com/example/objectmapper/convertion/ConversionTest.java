package com.example.objectmapper.convertion;

import com.example.objectmapper.dto.Bear;
import com.example.objectmapper.dto.convertion.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConversionTest {

    @Autowired
    public ObjectMapper objectMapper;

    /**
     * A JsonGetter will override the serialization and deserialization of the property. A JsonGetter works
     * ony on the getter.
     */
    @Test
    public void JsonGetter_willSerializeAndDeserializeTheProperty() throws JsonProcessingException {
        GetterToy toy = new GetterToy("Name of toy");
        String toyAsString = "{\"GetterName\":\"Name of toy\"}";
        String toyAsStringWithDefaultName = "{\"name\":\"Name of toy\"}";

        String expectedToyAsString = "{\"GetterName\":\"Name of toy\"}";
        GetterToy expectedToy = new GetterToy("Name of toy");

        String actualToyAsSting = objectMapper.writeValueAsString(toy);
        GetterToy actualToy = objectMapper.readValue(toyAsString, GetterToy.class);
        GetterToy actualToyWithDefaultName = objectMapper.readValue(toyAsStringWithDefaultName, GetterToy.class);

        // JsonGetter will serialize the property
        assertEquals(expectedToyAsString, actualToyAsSting);
        // JsonGetter will deserialize the property
        assertEquals(expectedToy.getName(), actualToy.getName());
        // The old property name is overwritten
        assertNull(actualToyWithDefaultName.getName());
    }

    /**
     * A JsonSetter will override the serialization and deserialization of the property. A JsonSetter works
     * ony on the setter.
     */
    @Test
    public void JsonSetter_willSerializeAndDeserializeTheProperty() throws JsonProcessingException {
        SetterToy toy = new SetterToy("Name of toy");
        String toyAsString = "{\"SetterName\":\"Name of toy\"}";
        String toyAsStringWithDefaultName = "{\"name\":\"Name of toy\"}";

        String expectedToyAsString = "{\"SetterName\":\"Name of toy\"}";
        SetterToy expectedToy = new SetterToy("Name of toy");

        String actualToyAsString = objectMapper.writeValueAsString(toy);
        SetterToy actualToy = objectMapper.readValue(toyAsString, SetterToy.class);
        SetterToy actualToyWithDefaultName = objectMapper.readValue(toyAsStringWithDefaultName, SetterToy.class);

        // JsonSetter will serialize the property
        assertEquals(expectedToyAsString, actualToyAsString);
        // JsonSetter will deserialize the property
        assertEquals(expectedToy.getName(), actualToy.getName());
        // The old property name is overwritten
        assertNull(actualToyWithDefaultName.getName());
    }

    /**
     * Using a JsonGetter and a JsonSetter will use the JsonGetter for serialization and JsonSetter for deserialization.
     */
    @Test
    public void JsonGetterAndJsonSetter_willSerializeAndDeserializeThePropertyWithDifferentNames() throws JsonProcessingException {
        GetterSetterToy toy = new GetterSetterToy("Name of toy");
        String toyAsString = "{\"SetterName\":\"Name of toy\"}";
        String toyAsStringWithDefaultName = "{\"name\":\"Name of toy\"}";

        GetterSetterToy expectedToy = new GetterSetterToy("Name of toy");
        String expectedToyAsString = "{\"GetterName\":\"Name of toy\"}";

        String actualToyAsString = objectMapper.writeValueAsString(toy);
        GetterSetterToy actualToy = objectMapper.readValue(toyAsString, GetterSetterToy.class);
        GetterSetterToy actualToyWithDefaultName = objectMapper.readValue(toyAsStringWithDefaultName, GetterSetterToy.class);

        // JsonGetter will serialize the property
        assertEquals(expectedToyAsString, actualToyAsString);
        // JsonSetter will deserialize the property
        assertEquals(expectedToy.getName(), actualToy.getName());
        // The old property name is overwritten
        assertNull(actualToyWithDefaultName.getName());
    }

    /**
     * A JsonProperty on getter will override the serialization and deserialization of the property.
     */
    @Test
    public void JsonPropertyOnGetter_willSerializeAndDeserializeTheProperty() throws JsonProcessingException {
        PropertyOnGetterToy toy = new PropertyOnGetterToy("Name of toy");
        String toyAsString = "{\"PropertyName\":\"Name of toy\"}";
        String toyAsStringWithDefaultName = "{\"name\":\"Name of toy\"}";

        String expectedToyAsString = "{\"PropertyName\":\"Name of toy\"}";
        PropertyOnGetterToy expectedToy = new PropertyOnGetterToy("Name of toy");

        String actualToyAsString = objectMapper.writeValueAsString(toy);
        PropertyOnGetterToy actualToy = objectMapper.readValue(toyAsString, PropertyOnGetterToy.class);
        PropertyOnGetterToy actualToyWithDefaultName = objectMapper.readValue(toyAsStringWithDefaultName, PropertyOnGetterToy.class);

        // JsonProperty will serialize the property
        assertEquals(expectedToyAsString, actualToyAsString);
        // JsonProperty will deserialize the property
        assertEquals(expectedToy.getName(), actualToy.getName());
        // The old property name is overwritten
        assertNull(actualToyWithDefaultName.getName());
    }

    /**
     * A JsonProperty on setter will override the serialization and deserialization of the property.
     */
    @Test
    public void JsonPropertyOnSetter_willSerializeAndDeserializeTheProperty() throws JsonProcessingException {
        PropertyOnSetterToy toy = new PropertyOnSetterToy("Name of toy");
        String toyAsString = "{\"PropertyName\":\"Name of toy\"}";
        String toyAsStringWithDefaultName = "{\"name\":\"Name of toy\"}";

        String expectedToyAsString = "{\"PropertyName\":\"Name of toy\"}";
        PropertyOnGetterToy expectedToy = new PropertyOnGetterToy("Name of toy");

        String actualToyAsString = objectMapper.writeValueAsString(toy);
        PropertyOnSetterToy actualToy = objectMapper.readValue(toyAsString, PropertyOnSetterToy.class);
        PropertyOnSetterToy actualToyWithDefaultName = objectMapper.readValue(toyAsStringWithDefaultName, PropertyOnSetterToy.class);

        // JsonProperty will serialize the property
        assertEquals(expectedToyAsString, actualToyAsString);
        // JsonProperty will deserialize the property
        assertEquals(expectedToy.getName(), actualToy.getName());
        // The old property name is overwritten
        assertNull(actualToyWithDefaultName.getName());
    }

    /**
     * Using a JsonProperty on getter and setter, will use the one over the setter for deserialization and
     * the one from getter for serialization.
     */
    @Test
    public void JsonPropertyOnGetterAndSetter_willSerializeAndDeserializeThePropertyWithDifferentNames() throws JsonProcessingException {
        PropertyOnGetterAndSetterToy toy = new PropertyOnGetterAndSetterToy("Name of toy");
        String toyAsString = "{\"SetterProperty\":\"Name of toy\"}";
        String toyAsStringWithDefaultName = "{\"name\":\"Name of toy\"}";

        PropertyOnGetterAndSetterToy expectedToy = new PropertyOnGetterAndSetterToy("Name of toy");
        String expectedToyAsString = "{\"GetterProperty\":\"Name of toy\"}";

        String actualToyAsString = objectMapper.writeValueAsString(toy);
        PropertyOnGetterAndSetterToy actualToy = objectMapper.readValue(toyAsString, PropertyOnGetterAndSetterToy.class);
        PropertyOnGetterAndSetterToy actualToyWithDefaultName = objectMapper.readValue(toyAsStringWithDefaultName, PropertyOnGetterAndSetterToy.class);

        // JsonProperty will serialize the property
        assertEquals(expectedToyAsString, actualToyAsString);
        // JsonProperty will deserialize the property
        assertEquals(expectedToy.getName(), actualToy.getName());
        // The old property name is overwritten
        assertNull(actualToyWithDefaultName.getName());
    }

    @Test
    public void nestingElementsOnDeserializationWithJsonCreator() throws JsonProcessingException {
        String json = "{\"name\": \"Beary\",\"size\": 10}";
        Bear bear = objectMapper.readValue(json, Bear.class);
        assertEquals("Beary", bear.getName());
        assertEquals(10, bear.getClaw().getSize());
    }
}
