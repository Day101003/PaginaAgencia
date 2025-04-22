package com.agencia.agencia.controller;

import com.agencia.agencia.security.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;

    public LoginController(AuthenticationManager authManager, JwtTokenProvider jwtProvider) {
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String correo,
                          @RequestParam String contrasena,
                          HttpServletResponse response) {

        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(correo, contrasena)
        );

        String token = jwtProvider.generarToken(auth);

        // Guardar el JWT en una cookie
        Cookie cookie = new Cookie("JWT_TOKEN", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1 hora
        response.addCookie(cookie);

        return "redirect:/index";
    }
}