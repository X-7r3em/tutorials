package com.example.jwtutil.deserialize;


import com.example.jwtutil.deserialize.model.ParsedToken;
import com.example.jwtutil.deserialize.model.SerializedToken;

public class JwtDeserializer {
    public void deserialize() {
        Reader reader = new Reader();
        SerializedToken serializedToken = reader.readData();
        JwtParser jwtParser = new JwtParser();
        ParsedToken token = jwtParser.parse(serializedToken);
        Writer writer = new Writer();
        writer.writeData(token);
    }
}
