package com.toeat.api.util;

import com.google.gson.Gson;
import com.toeat.api.exceptions.EtAuthException;
import com.toeat.api.model.Client;
import com.toeat.api.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtUtil {

    public static final String JWT_SECRET_KEY = "toeatapisecretkey";//TODO how to store it securely
    public static final long ACCESS_TOKEN_VALIDITY =  1 * 60 * 1000;// 1 minutes
    public static final long REFRESH_TOKEN_VALIDITY = 1 * 60 * 60 * 1000;// 1 HOUR

//    public static final long ACCESS_TOKEN_VALIDITY =  1 * 60 * 1000;
//    public static final long REFRESH_TOKEN_VALIDITY = 2 * 60 * 1000;

    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX  = "Bearer ";

    @Autowired
    private Gson gson;

    public static Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();

        String accessToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + ACCESS_TOKEN_VALIDITY))
                .claim("phone", user.getPhone())
                .claim("name", user.getName())
                .compact();

        String refreshToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + REFRESH_TOKEN_VALIDITY))
                .claim("phone", user.getPhone())
                .claim("name", user.getName())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("phone", user.getPhone());
        map.put("name", user.getName());
        map.put("accessToken",  accessToken);
        map.put("refreshToken",  refreshToken);
        return map;
    }

    public static Map<String, String> generateJWTToken(Client client) {
        long timestamp = System.currentTimeMillis();

        String accessToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + ACCESS_TOKEN_VALIDITY))
                .claim("phone", client.getPhone())
                .claim("name", client.getName())
                .claim("restaurantId", client.getRestaurantId())
                .compact();

        String refreshToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + REFRESH_TOKEN_VALIDITY))
                .claim("phone", client.getPhone())
                .claim("name", client.getName())
                .claim("restaurantId", client.getRestaurantId())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("phone", client.getPhone());
        map.put("name", client.getName());
        map.put("restaurantId", client.getRestaurantId().toString());
        map.put("accessToken",  accessToken);
        map.put("refreshToken",  refreshToken);
        return map;
    }

    public void verifyExpiration(String token) throws EtAuthException {
        try {
            Claims claims = Jwts.parser().setSigningKey(JwtUtil.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
            Date expDate = claims.getExpiration();

            if (expDate.toInstant().compareTo(Instant.now()) < 0) {
                String phone = (String) claims.get("phone");
                String name = (String)  claims.get("name");
                String restaurantId = (String)  claims.get("restaurantId");
                System.out.println();
            }

        } catch (ExpiredJwtException e) {
            throw new EtAuthException("invalid/expired token");
        }
    }
}

