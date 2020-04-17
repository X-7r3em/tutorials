package com.example.jwtutil.serialize;

import com.example.jwtutil.serialize.model.ResultToken;

import java.io.*;

import static com.example.jwtutil.common.Constants.*;

public class Writer {
    public void writeData(ResultToken token) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SERIALIZE_OUTPUT_PATH))) {
            bufferedWriter.write(JWT_PREFIX + token.getJwt());
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(SECRET_PREFIX + token.getSecret());
        } catch (IOException e) {
            System.out.println("Unable to read data");
        }
    }
}
