package com.toeat.api.controller;

import com.toeat.api.model.User;
import com.toeat.api.service.UserService;
import com.toeat.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signIn")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String password = (String) userMap.get("password");

        User user = userService.signIn(phone, password);

        return ResponseEntity.ok(jwtUtil.generateJWTToken(user));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String name = (String) userMap.get("name");
        String password = (String) userMap.get("password");
        String confirm = (String) userMap.get("confirm");

        User user = userService.signUp(phone, name, password, confirm);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/signUp").toUriString());
        return ResponseEntity.created(uri).body(jwtUtil.generateJWTToken(user));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> map) {
        String refreshToken = (String) map.get("refreshToken");
        String phone = jwtUtil.verifyClientToken(refreshToken);
        User user = userService.findByPhone(phone);
        return ResponseEntity.ok(jwtUtil.generateJWTToken(user));
    }
}
