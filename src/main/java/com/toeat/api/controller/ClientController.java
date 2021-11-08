package com.toeat.api.controller;

import com.toeat.api.model.Client;
import com.toeat.api.service.ClientService;
import com.toeat.api.service.UserService;
import com.toeat.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signIn")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String password = (String) userMap.get("password");

        Client client = clientService.signIn(phone, password);

        return ResponseEntity.ok(jwtUtil.generateJWTToken(client));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String name = (String) userMap.get("name");
        String password = (String) userMap.get("password");
        String confirm = (String) userMap.get("confirm");

        Client client = clientService.signUp(phone, name, password, confirm);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/signUp").toUriString());
        return ResponseEntity.created(uri).body(jwtUtil.generateJWTToken(client));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> map) {
        String refreshToken = (String) map.get("refreshToken");
        String phone = jwtUtil.verifyClientToken(refreshToken);
        Client client = clientService.findByPhone(phone);
        return ResponseEntity.ok(jwtUtil.generateJWTToken(client));
    }

}
