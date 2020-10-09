package com.example.jwtutil.common;

public class Constants {
    public static final String SERIALIZE_INPUT_PATH = System.getProperty("user.dir") + "/src/main/resources/serialize/input.json";
    public static final String SERIALIZE_OUTPUT_PATH = System.getProperty("user.dir") + "/src/main/resources/serialize/output.txt";
    public static final String DESERIALIZE_INPUT_PATH = System.getProperty("user.dir") + "/src/main/resources/deserialize/input.txt";
    public static final String DESERIALIZE_OUTPUT_PATH = System.getProperty("user.dir") + "/src/main/resources/deserialize/output.json";

    public static final String JWT_PREFIX = "JWT: ";
    public static final String SECRET_PREFIX = "secret: ";
}
