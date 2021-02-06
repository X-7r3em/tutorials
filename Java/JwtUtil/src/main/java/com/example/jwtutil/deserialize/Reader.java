package com.example.jwtutil.deserialize;

import com.example.jwtutil.deserialize.model.SerializedToken;

import java.io.*;

import static com.example.jwtutil.common.Constants.*;

public class Reader {
    public SerializedToken readData() {
        SerializedToken serializedToken = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DESERIALIZE_INPUT_PATH));
            String jwt = bufferedReader.readLine().replace(JWT_PREFIX, "");
            String secret = bufferedReader.readLine().replace(SECRET_PREFIX, "");
            serializedToken = new SerializedToken(jwt, secret);
        } catch (IOException e) {
            System.out.println("Unable to read token information");
        }

        return serializedToken;
    }
}
