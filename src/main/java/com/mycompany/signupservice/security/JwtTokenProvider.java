package com.mycompany.signupservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;


@Component
public class JwtTokenProvider {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(String email, String password) {
         return Jwts.builder()
                .setSubject(String.format("%s,%s", email, password))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        String subject = claims.getSubject();
        String[] parts = subject.split(",");

        String email = parts[0];
        String password = parts[1];

        UserDetails userDetails = new User(email, password, new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String generateValidAccessToken(String email, String password) {
        String jwtToken = null;
        if (email != null && password != null) {
            jwtToken = generateAccessToken(email, password);
            logger.info("Token was generated: {}", jwtToken);
        } else {
            logger.error("JWT Token could not be generated");
        }
        return jwtToken;
    }

}
