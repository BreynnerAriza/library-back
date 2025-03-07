package org.library.auth.jwt.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.library.auth.exception.TokenInvalidException;
import org.library.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class JwtService {

    private Algorithm algorithm;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.user}")
    private String userAt;

    @Value("${jwt.duration-min}")
    private Integer duration;

    //Inicializa el algoritmo una vez inyectada la clave secreta
    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(secretKey);
    }

    //Permite generar nuevos JWT
    public String generateToken(User user){
        try {
            return JWT.create()
                    .withJWTId(UUID.randomUUID().toString())
                    .withSubject(user.getUsername())
                    .withIssuer(userAt)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + Duration.ofMinutes(duration).toMillis()))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            log.info("Error al crear el JWT");
        }
        return "";
    }

    //Permite validar si un token es valido o no
    public boolean validateToken(String token){
        try {
           JWT.require(algorithm)
                   .withIssuer(userAt)
                   .build()
                   .verify(token);
           return true;
        } catch (JWTVerificationException exception){
            log.info("Token invalido");
            throw new TokenInvalidException("El token proporcionado es invalido");
        }
    }

    //Obtener el username a partir de un JWT
    public String getSubject(String token){
        try {
            return JWT.require(algorithm)
                    .withIssuer(userAt)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            log.info("Token invalido al obtener el subject");
            throw new TokenInvalidException("El token proporcionado es invalido");
        }
    }

}
