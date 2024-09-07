package com.lucasmacedo.pedidos.OrderEvent.controller;

import com.lucasmacedo.pedidos.OrderEvent.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/token")
    public String generateToken(@RequestParam String username) {
        return JwtUtil.generateToken(username);
    }

}
