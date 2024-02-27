package com.example.Backend.TokenFunctional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class TokenValueGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);;
    public static String generator(UUID id, String login){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000; // Текущее время + 1 час
        Date exp = new Date(expMillis);
        return Jwts.builder()
                .setId(id.toString())
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }
}
