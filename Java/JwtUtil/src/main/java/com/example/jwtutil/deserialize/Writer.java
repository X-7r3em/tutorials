package com.example.jwtutil.deserialize;

import com.example.jwtutil.deserialize.model.ParsedToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static com.example.jwtutil.common.Constants.DESERIALIZE_OUTPUT_PATH;

public class Writer {
    public void writeData(ParsedToken token) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(DESERIALIZE_OUTPUT_PATH), token);
        } catch (IOException e) {
            System.out.println("Unable to write token");
        }
    }
}
