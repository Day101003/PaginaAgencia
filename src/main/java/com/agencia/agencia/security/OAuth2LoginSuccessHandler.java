package com.agencia.agencia.security;

import com.agencia.agencia.model.Usuario;
import com.agencia.agencia.service.UsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioService usuarioService;
    private final JwtTokenProvider jwtTokenProvider;

    public OAuth2LoginSuccessHandler(UsuarioService usuarioService, JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attrs = oauthUser.getAttributes();

        // Crear un payload simulado (email, name, picture)
        GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
        payload.setEmail((String) attrs.get("email"));
        payload.set("name", attrs.get("name"));
        payload.set("picture", attrs.get("picture"));

        // Login o registro
        Usuario usuario = usuarioService.loginOrRegister(payload);

        // 🛠 Corregido: ahora pasamos los tres datos necesarios al token
        String token = jwtTokenProvider.generateToken(
            usuario.getCorreo(),
            usuario.getNombre(),
            usuario.getRuta_imagen_usuario()
        );

        // Enviar el token en una cookie segura
        Cookie jwtCookie = new Cookie("JWT_TOKEN", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(3600); // 1 hora
        response.addCookie(jwtCookie);

        // Redirigir al home
        response.sendRedirect("/index");
    }
}