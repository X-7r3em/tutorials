package com.example.jwtutil.deserialize;

import com.example.jwtutil.deserialize.model.ParsedToken;
import com.example.jwtutil.deserialize.model.SerializedToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtParser {
    public ParsedToken parse(SerializedToken serializedToken) {
        try {
            Jws<Claims> jws = Jwts
                    .parser()
                    .setSigningKey(serializedToken.getSecret())
                    .parseClaimsJws(serializedToken.getJwt());
            return new ParsedToken(jws.getHeader(), jws.getBody(), true);
        } catch (Exception ex) {
            return  new ParsedToken(null, null, false);
        }
    }
}
