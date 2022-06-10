package com.cursojava.curso.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log= LoggerFactory.getLogger(JWTUtils.class);

    public String create(String id, String subject)
    {
        long nowMillis=System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //Devolviendo la palabra secreta de JWT.
        byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(key);
        Key singningKey= new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Inicializamos el JWT Claims.
        JwtBuilder builder=Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject)
                           .setIssuer(issuer).signWith(signatureAlgorithm, singningKey);

        if(ttlMillis>=0)
        {
            long expMillis=nowMillis+ttlMillis;
            Date exp= new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public String getValue(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getKey(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();

        return claims.getId();
    }
}
