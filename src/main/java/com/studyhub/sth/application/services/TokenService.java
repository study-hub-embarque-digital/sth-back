package com.studyhub.sth.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyhub.sth.application.dtos.jitsi.JitsiContext;
import com.studyhub.sth.application.dtos.jitsi.JitsiFeature;
import com.studyhub.sth.application.dtos.jitsi.JitsiUser;
import com.studyhub.sth.application.dtos.rooms.GeneratedRoomDto;
import com.studyhub.sth.domain.entities.Permissao;
import com.studyhub.sth.domain.entities.RefreshToken;
import com.studyhub.sth.domain.entities.Role;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.IRefreshTokenRepository;
import nonapi.io.github.classgraph.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${jitsi.private-key}")
    private String privKey;
    @Value("${jitsi.public-key}")
    private String publicKeyPem;
    @Value("${jitsi.key-id}")
    private String jitsiKeyId;

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

    public String generateJitsiToken(Usuario usuario, String room) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");

            // 1) Constrói o PrivateKey a partir da string PEM
            String privatePemClean = privKey
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] privateBytes = Base64.getDecoder().decode(privatePemClean);
            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateBytes);
            PrivateKey privateKey = kf.generatePrivate(privateSpec);

            // 2) Constrói o PublicKey a partir da string PEM
            String publicPemClean = publicKeyPem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] publicBytes = Base64.getDecoder().decode(publicPemClean);
            X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicBytes);
            PublicKey publicKey = kf.generatePublic(publicSpec);

            Algorithm algorithm = Algorithm.RSA256(
                    (RSAPublicKey) publicKey,
                    (RSAPrivateKey) privateKey
            );


            JitsiContext jitsiContext = jitsiContext(usuario, room);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> context = objectMapper.convertValue(jitsiContext, Map.class);

            String token = JWT.create()
                    .withKeyId(jitsiKeyId)
                    .withIssuer("chat")
                    .withSubject("vpaas-magic-cookie-5949bf32cbbe4eb082c593fa88929944")
                    .withAudience("jitsi")
                    .withExpiresAt(generateExpirationTimeJitsi())
                    .withClaim("context", context)
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar Jitsi token");
        }
    }

    public Instant generateExpirationTimeJitsi() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public JitsiContext jitsiContext(Usuario u, String room) {
        JitsiContext jitsiContext = new JitsiContext();
        jitsiContext.setRoom(room);

        JitsiUser jitsiUser = new JitsiUser();
        jitsiUser.setModerator(true);
        jitsiUser.setId(u.getUsuarioId().toString());
        jitsiUser.setName(u.getNome());
        jitsiUser.setEmail(u.getEmail());
        jitsiUser.setHiddenFromRecorder(false);

        jitsiContext.setUser(jitsiUser);

        JitsiFeature jitsiFeature = new JitsiFeature();
        jitsiFeature.setLivestreaming(false);
        jitsiFeature.setOutboundCall(true);
        jitsiFeature.setSipOutboundCall(true);
        jitsiFeature.setRecording(true);
        jitsiFeature.setTranscription(true);

        jitsiContext.setFeatures(jitsiFeature);

        return jitsiContext;
    }
}
