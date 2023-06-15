package org.rakesh.practice.blogapi.security.jwt;

import org.rakesh.practice.blogapi.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class JWTAuthenticationFilter extends AuthenticationFilter {

    public JWTAuthenticationFilter() {
        super(new JWTAuthenticationManager(), new JWTAuthenticationConverter());
        setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }

    //converts httprequest object to jwtauthenctication/cookieauthentication/.... object;
    // it checks whether the data is in certain format or not.
    //
    static class JWTAuthenticationConverter implements AuthenticationConverter {

        @Override
        public Authentication convert(HttpServletRequest request) {
            if(request.getHeader("authorization")!=null){
                String token = request.getHeader("authorization").split(" ")[1];
                return new JWTAuthentication(token);
            }
            return null;
        }
    }

    /*
    We need to create class that implements AuthenticationManager and AuthenticationConverter class
    we can these as a separate class in the jwt package or inside this class itself as these are related
    classes
     */

    // In authentication step it tries to validate if the provided jwt is valid or not.
    //first it converts using AuthenticationConverter and then it uses Authentication
    static class JWTAuthenticationManager implements AuthenticationManager {

        //ideally this should be auto injected; however we have just initialized here.
        private JWTService jwtService = new JWTService();

        //private UsersService usersService = new UsersService();


        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            if(authentication instanceof JWTAuthentication){
                JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
                String token = jwtAuthentication.getCredentials();
                if(token != null){
                   Long userId = jwtService.getUseridFromJWT(token);
                   jwtAuthentication.setUserId(userId);

                    return authentication;
                }
            }

            return null;
        }
    }


}
