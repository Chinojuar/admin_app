package com.intranet.demo.security.JWT;
import com.intranet.demo.security.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private static  final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private final String PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String SECRET;


    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
         String jwt = Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, StringUtils.getBytes(SECRET, StandardCharsets.UTF_8))
                .compact();
        return PREFIX + jwt;
    }

    public String getNombreUsuarioFromToken(String token){
        String jwtToken = token.replace(PREFIX, "");
        byte[] secret = SECRET.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            String jwtToken = token.replace(PREFIX, "");
            byte[] secret = SECRET.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("token expirado " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("token vacío " +e.getMessage());
        } catch (SignatureException e) {
            logger.error("error en la firma " +e.getMessage());
        }
        return false;
    }
}