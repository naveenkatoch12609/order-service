package com.warehouse.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.order_service.util.JwtUtil;

@RestController
@RequestMapping("/test")
public class TokenTestController {

	@Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/token")
    public ResponseEntity<String> generateToken() {
        String token = jwtUtil.generateToken("test-user");
        return ResponseEntity.ok(token);
    }
    
}
