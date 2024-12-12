package com.studyhub.sth.application.annotations;

import com.studyhub.sth.domain.annotations.AuthorizeWithRole;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuthorizeWithRoleImpl {
    @Before("@within(roleRequired) || @annotation(roleRequired)")
    public void checkRole(AuthorizeWithRole authorizeWithRole) {
        // Obtém o contexto de autenticação
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Usuário não autenticado.");
        }

        // Recupera as roles permitidas da annotation
        String[] allowedRoles = authorizeWithRole.roles();

        // Verifica se o usuário tem pelo menos uma das roles permitidas
        boolean hasRole = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> Arrays.asList(allowedRoles).contains(role));

        if (!hasRole) {
            throw new SecurityException("Acesso negado. Role insuficiente.");
        }
    }
}
