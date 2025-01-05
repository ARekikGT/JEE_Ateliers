package com.tn.atelier.ahmed_rekik.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    
    @SuppressWarnings({ "deprecation", "removal" })
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeRequests(requests -> requests.requestMatchers("/login").permitAll());
        http.authorizeRequests(requests -> requests.anyRequest().authenticated());
    }
}
