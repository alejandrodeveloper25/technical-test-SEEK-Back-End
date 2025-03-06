package com.algonzjimz.tecnical_test.secutiry;


import com.algonzjimz.tecnical_test.exception.JwtValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "TestSEEK070320251500AlejandroGonzalez"; // Usa una clave más segura
    private static final long EXPIRATION_TIME = 86400000; // 1 día

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new JwtValidationException("Token expirado", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtValidationException("Token no soportado", e);
        } catch (MalformedJwtException e) {
            throw new JwtValidationException("Token mal formado", e);
        } catch (SignatureException e) {
            throw new JwtValidationException("Firma del token inválida", e);
        } catch (IllegalArgumentException e) {
            throw new JwtValidationException("Token vacío o inválido", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            throw new JwtValidationException("Error de validación del token", e);
        }
    }


}
