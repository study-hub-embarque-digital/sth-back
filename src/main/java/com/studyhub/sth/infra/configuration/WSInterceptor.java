package com.studyhub.sth.infra.configuration;

import com.studyhub.sth.application.services.TokenService;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class WSInterceptor implements HandshakeInterceptor {
    private final TokenService tokenService;
    private final IUsuarioRepository usuarioRepository;

    public WSInterceptor(TokenService tokenService, IUsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            String token = httpServletRequest.getParameter("token");
            var login = tokenService.validateToken(token);

            if (login != null) {
                attributes.put("user", extrairUsuario(login));
                return true;
            }
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }

    private boolean validarToken(String token) {
        // Aqui você implementa a validação do JWT
        return true;
    }

    private Usuario extrairUsuario(String login) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(login)).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
//        var authorities = Collections.singletonList(usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome())));
//
//        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return usuario;
    }
}
