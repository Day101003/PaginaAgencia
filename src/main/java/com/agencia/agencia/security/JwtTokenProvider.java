package com.agencia.agencia.security;

import com.agencia.agencia.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-millis}")
    private long jwtExpirationMillis;

    /**
     * Genera token desde Authentication (usado en login manual)
     */
    public String generarToken(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        String email;
        String nombre = "";
        String imagen = "";

        if (principal instanceof Usuario usuario) {
            email = usuario.getCorreo();
            nombre = usuario.getNombre();
            imagen = usuario.getRuta_imagen_usuario();
        } else if (principal instanceof org.springframework.security.core.userdetails.User userDetails) {
            email = userDetails.getUsername();
        } else {
            email = authentication.getName();
        }

        return buildToken(email, nombre, imagen);
    }

    /**
     * Genera token desde datos directos (por ejemplo, OAuth2 login)
     */
    public String generateToken(String email, String nombre, String rutaImagen) {
        return buildToken(email, nombre, rutaImagen);
    }

    private String buildToken(String email, String nombre, String imagen) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMillis);
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(email)
                .claim("nombre", nombre)
                .claim("imagen", imagen)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida si el JWT es correcto
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("❌ Token inválido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el correo (subject) desde el token
     */
    public String getUsernameDelJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extrae todos los claims (para uso en el frontend)
     */
    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
