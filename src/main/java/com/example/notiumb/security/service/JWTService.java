package com.example.notiumb.security.service;

import com.example.notiumb.model.User;
import com.example.notiumb.repository.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private IUserRepository userRepository;

    public String extractId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    public boolean isTokenValid(String token, User usuario) {
        Integer id = Integer.valueOf(extractId(token));
        User user = userRepository.findTopById(id);
        String username = user.getUsername();
        return (username.equals(usuario.getUsername())) && !isTokenExpired(token);

    }

    public String generateToken(Map<String, Object> extraClaims, User usuario) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(String.valueOf(usuario.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private Key getSignInKey() {
        // Decodifica la clave hexadecimal
        byte[] keyBytes = new byte[secretKey.length() / 2];
        for (int i = 0; i < keyBytes.length; i++) {
            keyBytes[i] = (byte) Integer.parseInt(secretKey.substring(2 * i, 2 * i + 2), 16);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}