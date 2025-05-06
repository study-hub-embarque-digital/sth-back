package com.studyhub.sth.application.annotations;

import com.studyhub.sth.domain.before.annotations.AuthorizeWithPermission;
import com.studyhub.sth.domain.before.entities.Permissao;
import com.studyhub.sth.domain.before.entities.Role;
import com.studyhub.sth.domain.before.entities.Usuario;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuthorizeWithPermissionsImpl {
    @Before("@annotation(authorizeWithPermission)")
    public void checkRole(AuthorizeWithPermission authorizeWithPermission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Usuário não autenticado.");
        }

        String[] allowedPermissions = authorizeWithPermission.permissions();

        Usuario currentUser = (Usuario) authentication.getPrincipal();

        List<Role> roles = currentUser.getRoles();
        List<List<Permissao>> listOfRolesListsOfPermissoes = roles.stream().map(Role::getPermissoes).toList();
        List<Permissao> listOfPermissions = listOfRolesListsOfPermissoes.stream()
                .flatMap(List::stream)
                .toList();

        if (listOfPermissions.isEmpty()) {
            throw new SecurityException("Você não possui permissão para acessar essa rota!");
        }

        String[] permissionsNames = listOfPermissions.stream().map(Permissao::getNome).distinct().toArray(String[]::new);

        boolean hasPermission = Arrays.stream(permissionsNames).toList().stream().anyMatch(permission -> Arrays.asList(allowedPermissions).contains(permission));

        if (!hasPermission) {
            throw new SecurityException("Você não possui permissão para acessar essa rota!");
        }
    }
}
