package com.bhanuka.backend.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

// class for write the methods for craete and validate token
@Component
public class JwtUtils {

    // google search "generate 256bit secret key, and we dont use it in source code.
    // create a variable and store it in application.properties"
    @Value("${app.secret}") // app.properties
    private String secret;

    // java.security.key package and must use the packages in this code
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); // create the key
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // we can create a token using userdetails

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))// 86400000 is miliseconds of 24hours, expires
                // in 24h from now
                .signWith(key(), SignatureAlgorithm.HS256)// sign with the key
                .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (MalformedJwtException e) { // malformedjwtexception comes when token is chnaged
            System.err.println("token has been changed, Invalid");
        } catch (ExpiredJwtException e) {// when the token is expired
            System.err.println("Token is expired");
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported token");
        } catch (IllegalArgumentException e) {
            System.err.println("Blank token");
        }

        return false;
    }

    public String getUsernameFromToken(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwtToken).getBody().getSubject();
    }
}
