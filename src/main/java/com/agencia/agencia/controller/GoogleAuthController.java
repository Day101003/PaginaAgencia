package com.agencia.agencia.controller;
import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.security.JwtTokenProvider;
import com.agencia.agencia.service.UsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/google")
public class GoogleAuthController {

    private final UsuarioService usuarioService;
    private final JwtTokenProvider jwtProvider;
    private final GoogleIdTokenVerifier verifier;

    public GoogleAuthController(
            UsuarioService usuarioService,
            JwtTokenProvider jwtProvider,
            @Value("${spring.security.oauth2.client.registration.google.client-id}")
            String clientId
    ) {
        this.usuarioService = usuarioService;
        this.jwtProvider = jwtProvider;
        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance()
        )
        .setAudience(Collections.singletonList(clientId))
        .build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> loginConGoogle(
            @RequestBody Map<String, String> body,
            HttpServletResponse response
    ) {
        System.out.println("🔔 Petición recibida en /api/auth/google");

        String credential = body.get("credential");
        if (credential == null || credential.isEmpty()) {
            System.out.println("❌ Token de Google ausente o vacío");
            return ResponseEntity.badRequest().build();
        }

        try {
            GoogleIdToken idToken = verifier.verify(credential);
            if (idToken == null) {
                System.out.println("❌ Token inválido");
                return ResponseEntity.status(401).build();
            }

            var payload = idToken.getPayload();
            System.out.println("📧 Email: " + payload.getEmail());
            System.out.println("👤 Nombre: " + payload.get("name"));

            Usuario usuario = usuarioService.loginOrRegister(payload);

            var userDetails = usuarioService.loadUserByUsername(usuario.getCorreo());
            var auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            String token = jwtProvider.generarToken(auth);

            Cookie jwtCookie = new Cookie("token", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(86400);
            // Agregá estas dos si estás en https en producción:
            // jwtCookie.setSecure(true);
            // jwtCookie.setDomain("tudominio.com");
            response.addCookie(jwtCookie);

            System.out.println("🎉 Login exitoso, cookie establecida");

            Map<String, String> res = new HashMap<>();
            res.put("redirectUrl", "/index");
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            System.out.println("🔥 Excepción durante login/register con Google: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}