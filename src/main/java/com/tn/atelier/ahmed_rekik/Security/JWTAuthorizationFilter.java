package com.tn.atelier.ahmed_rekik.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7); // Remove "Bearer " prefix
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); // Replace "secret" with your secure key
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String username = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }
}
