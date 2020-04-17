package com.example.jwtutil.serialize;

import com.example.jwtutil.serialize.model.DeserializedToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import static com.example.jwtutil.common.Constants.SERIALIZE_INPUT_PATH;

public class Reader {
    public DeserializedToken readData() {
        ObjectMapper objectMapper = new ObjectMapper();
        DeserializedToken deserializedToken = null;
        try {
            deserializedToken = objectMapper.readValue(new File(SERIALIZE_INPUT_PATH), DeserializedToken.class);
        } catch (IOException e) {
            System.out.println("Unable to read token information");
        }

        return deserializedToken;
    }
}
