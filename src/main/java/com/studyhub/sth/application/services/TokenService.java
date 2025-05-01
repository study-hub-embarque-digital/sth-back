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
    private final String privKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCdQ2ha7ZW+RvAa\n" +
            "G2aGDvZt2yBiki5qhnBq/NJjsBKIYsaThPU80rBSngv8XXGtMiep/A5+GyhKiBvf\n" +
            "UVLUEyQNPmK53UPRe89X5vEdjSP3bSiqWGam7Ehfz0XggUHDpfNHznyij9eytKKF\n" +
            "n44cpnZPZL4MgaSfxb5+PDlJQslN/JbtAk1EptBLEV9vQpfifyNAnCQjiLOn1EJn\n" +
            "d7j8r/4Fq3auQ7ilkIQNrb3pVbD4NQj6BJhoGy1i9yBzd4IU7KA+EseWsibKtisI\n" +
            "ploKkf6ttz44XUXODKS9RTcoafoyYcsaH/Jp2Ul2bzyW4lpa51dAlymUouugo1YU\n" +
            "fIuqsExPAgMBAAECggEAA2omRusnuDT0G60CoeCLS0FZx6oi5d87a6dHrjBxhpKW\n" +
            "ezeE16g/edvCqN0ijb8sC03UxroyOsQPzt6GLOKpgrwbCqwaP98vVlpGW/znjgTJ\n" +
            "wEEhIXi57ZKjksdbtTLSQHnkTOdQqKmxabNYM5kNspWeZskd2PZDN5x5JfUUR+Qu\n" +
            "YFrj22d+LzvRULc6Y65SswFgx8XHOqigiaYcGSXhKDCFyHtAphfhzpHjwGVcxFSS\n" +
            "8i+EBUjSYLkrcEu6nv5zLzB65j9lYEusPi3LDigv+zwK96mzHjNDIwiqWkO9dFb1\n" +
            "zH+s9HaqYLVOJ0xVn82RS/I5o6MJji5iFWo/BIlSUQKBgQDh9iahw/HpTy/zEPN0\n" +
            "tQgpwt1hciht3pGOXy10XfFUc0KPB/FUh3ogYtj3BkXP5YLgxvKPb7aB261Ao/PA\n" +
            "IkIogaxlwGU51+VLwM5xG/cKk/72crbcjrQMYLEhYEXt/oIObnllNvZZx2gHVEA1\n" +
            "OUukjv136zY7YObhi/Gd9VrtlwKBgQCyK1dncm50b46R3zNp5TIvCvQ61w4OqF1L\n" +
            "GZ/ETcsFTy8pUCR+JBvN6Gl/aiXMQn0JTLaboEURb0NeYKpFgX64NKJ0DmBsMcXN\n" +
            "IyMRDx+yLjlp04/2fPvim5RgGAAeLa7HYc46a0OSbWvMROJiRenv8qGBHPrGsoZB\n" +
            "1mF5K9PeCQKBgQCSB7R7wthqQYTpf0D3Ya9+3bKYsWAzcS18Z0JG/BdkzoBrU2TB\n" +
            "jjR9DaTOMD2Z1+e3QJut2zKFxeS3670xpHJBH1y8/ZPtx/sl89r2+m2zZmXV+9j1\n" +
            "vTva6/pNaZyH7H08umS15slayCYQ5oAAZaDfHpHsmBQaV8ueZASoYtJ6zQKBgHK2\n" +
            "KwEkkO8QgDd1AHI2qdfV8qcLnTZcuixHJDFMcOFLOS8dNVGtx+ULtRje69UWHdDl\n" +
            "/lA2oSF3hGV4UUiM9lx9LvcP5o7igNrxu3sZRKwAzOBQ4Uiu8bHVv3MbIMBNY1Fl\n" +
            "rFS6iPf20UfkNelV4CeoDMnHMcLKYFx1Pa65RCCZAoGBAIOBs2L9XbWpCxVC+ES1\n" +
            "2XEdJ3Y4D8GX4BmcjBwyCNGFMvvkKBQYAb4tEi6hjYlJkaMamoJttHoXBsTeUUfD\n" +
            "J/Unue51oQDc48dU2we4b1izwBoM6eg9rtIo32lyH0NnzZwwZ4wbTDoiEXvfvdYk\n" +
            "dB+6BLWDW2EiLmE2jhEr5ktW\n" +
            "-----END PRIVATE KEY-----";

    private final String publicKeyPem = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnUNoWu2VvkbwGhtmhg72\n" +
            "bdsgYpIuaoZwavzSY7ASiGLGk4T1PNKwUp4L/F1xrTInqfwOfhsoSogb31FS1BMk\n" +
            "DT5iud1D0XvPV+bxHY0j920oqlhmpuxIX89F4IFBw6XzR858oo/XsrSihZ+OHKZ2\n" +
            "T2S+DIGkn8W+fjw5SULJTfyW7QJNRKbQSxFfb0KX4n8jQJwkI4izp9RCZ3e4/K/+\n" +
            "Bat2rkO4pZCEDa296VWw+DUI+gSYaBstYvcgc3eCFOygPhLHlrImyrYrCKZaCpH+\n" +
            "rbc+OF1FzgykvUU3KGn6MmHLGh/yadlJdm88luJaWudXQJcplKLroKNWFHyLqrBM\n" +
            "TwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

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
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");
            byte[] privateBytes = Base64.getDecoder().decode(privatePemClean);
            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateBytes);
            PrivateKey privateKey = kf.generatePrivate(privateSpec);

            // 2) Constrói o PublicKey a partir da string PEM
            String publicPemClean = publicKeyPem
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
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
                    .withKeyId("vpaas-magic-cookie-5949bf32cbbe4eb082c593fa88929944/5ba92b")
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
