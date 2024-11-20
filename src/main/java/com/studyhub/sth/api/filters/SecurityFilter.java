package com.studyhub.sth.api.filters;

import com.studyhub.sth.application.services.TokenService;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Value("${api.environment.name}")
    String ambiente;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        var login = this.ambiente.equals("DESENVOLVIMENTO") && token.equals("ola mundo") ? "sistema" : tokenService.validateToken(token);

        if (login != null) {
            Usuario usuario = login.equals("sistema") ? this.getSystemUser() : usuarioRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    private Usuario getSystemUser() {
        Usuario u = new Usuario();
        u.setNome("Sistema");
        u.setUsuarioId(UUID.fromString("f6d39ae1-748d-48c2-a160-f2fd4a0929cf"));

        return u;
    }
}
