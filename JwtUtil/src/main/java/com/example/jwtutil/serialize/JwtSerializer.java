package com.example.jwtutil.serialize;

import com.example.jwtutil.serialize.model.DeserializedToken;
import com.example.jwtutil.serialize.model.ResultToken;

public class JwtSerializer {
    public void serialize() {
        Reader reader = new Reader();
        DeserializedToken deserializedToken = reader.readData();
        JwtBuilder jwtBuilder = new JwtBuilder();
        ResultToken jwt = jwtBuilder.build(deserializedToken);
        Writer writer = new Writer();
        writer.writeData(jwt);
    }
}
