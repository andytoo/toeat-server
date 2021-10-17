package com.toeat.api.controller;

import com.toeat.api.config.AuthenticationConfigConstants;
import com.toeat.api.model.User;
import com.toeat.api.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String password = (String) userMap.get("password");

        User user = userService.signIn(phone, password);

        return ResponseEntity.ok(generateJWTToken(user));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String name = (String) userMap.get("name");
        String password = (String) userMap.get("password");
        String confirm = (String) userMap.get("confirm");

        User user = userService.signUp(phone, name, password, confirm);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/signUp").toUriString());
        return ResponseEntity.created(uri).body(generateJWTToken(user));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> map) {
        String refreshToken = (String) map.get("refresh_token");//TODO
        return ResponseEntity.ok(new HashMap<>());
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();

        String accessToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, AuthenticationConfigConstants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + AuthenticationConfigConstants.ACCESS_TOKEN_VALIDITY))
                .claim("phone", user.getPhone())
                .claim("name", user.getName())
                .claim("restaurantId", user.getRestaurantId())
                .compact();

        String refreshToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, AuthenticationConfigConstants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + AuthenticationConfigConstants.REFRESH_TOKEN_VALIDITY))
                .claim("phone", user.getPhone())
                .claim("name", user.getName())
                .claim("restaurantId", user.getRestaurantId())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("phone", user.getPhone());
        map.put("name", user.getName());
        map.put("restaurantId", user.getRestaurantId().toString());
        map.put("access_token",  accessToken);
        map.put("refresh_token",  refreshToken);
        return map;
    }

}
