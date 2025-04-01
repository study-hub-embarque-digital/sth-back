package com.studyhub.sth.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.studyhub.sth.domain.entities.Permissao;
import com.studyhub.sth.domain.entities.RefreshToken;
import com.studyhub.sth.domain.entities.Role;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.IRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private IRefreshTokenRepository refreshTokenRepository;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String[] rolesNames = usuario.getRoles().stream().map(Role::getNome).toArray(String[]::new);

            List<Role> roles = usuario.getRoles();
            List<List<Permissao>> listOfRolesListsOfPermissoes = roles.stream().map(Role::getPermissoes).toList();
            List<Permissao> listOfPermissions = listOfRolesListsOfPermissoes.stream()
                    .flatMap(List::stream)
                    .toList();

            String[] permissions = new String[0];

            if (!listOfPermissions.isEmpty()) {
                permissions = listOfPermissions.stream().map(Permissao::getNome).distinct().toArray(String[]::new);
            }

            String token = JWT.create()
                    .withIssuer("sth-spring")
                    .withSubject(usuario.getUsuarioId().toString())
                    .withExpiresAt(generateExpirationTime())
                    .withClaim("name", usuario.getNome())
                    .withClaim("email", usuario.getEmail())
                    .withArrayClaim("roles", rolesNames)
                    .withArrayClaim("permissions", permissions)
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro enquanto autenticava");
        }
    }

    public String generateRefreshToken(Usuario usuario) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsuario(usuario);
        refreshToken.setExpiration(LocalDateTime.now().plusMonths(6).toInstant(ZoneOffset.of("-03:00")));
        this.refreshTokenRepository.save(refreshToken);
        return refreshToken.getKey().toString();
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("sth-spring")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public Usuario validateRefreshToken(String refreshTokenKey) throws Exception {
        RefreshToken refreshToken = this.refreshTokenRepository.findById(UUID.fromString(refreshTokenKey)).orElseThrow(() -> new Exception("Refresh não existe!"));
        Usuario u = refreshToken.getUsuario();
        this.refreshTokenRepository.delete(refreshToken);
        if (refreshToken.getExpiration().isBefore(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")))) throw new Exception("Refresh inválido!");

        return u;
    }

    public Instant generateExpirationTime() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
