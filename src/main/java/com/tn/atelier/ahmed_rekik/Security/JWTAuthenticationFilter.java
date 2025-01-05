package com.tn.atelier.ahmed_rekik.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tn.atelier.ahmed_rekik.Entity.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @SuppressWarnings("unused")
    private final AuthenticationManager authenticationManager = null;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read authentication request", e);
        }
    }


    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        List<String> roles = new ArrayList<>();
        authResult.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));

        String token = JWT.create()
                .withSubject(username)
                .withArrayClaim("roles", roles.toArray(new String[0]))
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .sign(Algorithm.HMAC256("secret")); // Replace "secret" with your secure key

        response.addHeader("Authorization", "Bearer " + token);
    }
}
