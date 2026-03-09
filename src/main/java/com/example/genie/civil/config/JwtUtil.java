package com.example.genie.civil.config;

import com.example.genie.civil.entity.Role;
import com.example.genie.civil.entity.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String SECRET = "mysupersecretkeymysupersecretkeymysupersecretkey";
    private final long EXPIRATION = 86400000; // 24h

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ---------------- TOKEN GENERATION ----------------

    public String generateToken(Utilisateur user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getIdUtilisateur());
        claims.put("nom", user.getNom());
        claims.put("prenom", user.getPrenom());
        claims.put("email", user.getEmail());

        claims.put(
                "roles",
                user.getRoles()
                        .stream()
                        .map(Role::getNomRole)
                        .collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user.getIdUtilisateur()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ---------------- TOKEN READING ----------------

    public Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserId(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Object extractRoles(String token) {
        return extractAllClaims(token).get("roles");
    }

    // ---------------- TOKEN VALIDATION ----------------

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;

        }
    }
}