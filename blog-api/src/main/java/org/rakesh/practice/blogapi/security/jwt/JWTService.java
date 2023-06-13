package org.rakesh.practice.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private Algorithm algorithm = Algorithm.HMAC256("SECRET SIGNING KEY (should be env or config");
    public String createJWT(Long userId){

        String token = JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*24*7))
                .sign(algorithm);

        return token;

    }


    public Long getUseridFromJWT(String jwt){

        try{
            var verifier = JWT.require(algorithm).build();
            var decodedJWT = verifier.verify(jwt);
            var subject = decodedJWT.getSubject();
            return Long.parseLong(subject);
        }catch(Exception e){
           e.printStackTrace();
        }
        return null;
    }
}
