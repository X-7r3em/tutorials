package com.example.jwtutil.serialize;

import com.example.jwtutil.serialize.model.DeserializedToken;
import com.example.jwtutil.serialize.model.ResultToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtBuilder {
    public ResultToken build(DeserializedToken deserializedToken) {
        String jwt = Jwts
                .builder()
                .setClaims(deserializedToken.getPayload())
                .signWith(SignatureAlgorithm.HS256, deserializedToken.getSecret())
                .compact();
        return new ResultToken(jwt, deserializedToken.getSecret());
    }
}
