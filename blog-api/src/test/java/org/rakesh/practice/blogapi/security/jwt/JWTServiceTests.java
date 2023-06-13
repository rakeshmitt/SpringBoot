package org.rakesh.practice.blogapi.security.jwt;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTServiceTests {

    private JWTService jwtService = new JWTService();

    @Test
    void canCreateJWTFromUserid(){

        var userid = 12345L;
        var jwt = jwtService.createJWT(userid);
        //this test would fail as we are always generating a current date;
        //if we want to test that methods, we need to use another methods where issuedat and expiryAt is passed as input.
        assertEquals("aaa", jwt);
    }

    @Test
    void canVerifyJWT(){
        var jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NSIsImV4cCI6MTY4NzIyNzM0OSwiaWF0IjoxNjg2NjIyNTQ5fQ.kCjADYdemET4JK_nd_DH8Y5MR-CzoM6izcOWUjA4mII";
        var userid = jwtService.getUseridFromJWT(jwt);
        assertEquals(12345, userid);
    }
}
