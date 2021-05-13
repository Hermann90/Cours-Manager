/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hermann90.CoursManager.entities.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.awt.SystemColor;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *This class perform filtering 
 * 
 * @author hermann90
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
            throws AuthenticationException {
        
        /* --------- take Json object and store on AppUser Oject ------------*/
        AppUser appUser = null;
        try {
            appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("********************************");
        System.out.println("User name : "+ appUser.getUserName());
        System.out.println("User PWD : "+ appUser.getPassword());
        System.out.println("********************************");

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appUser.getUserName(), appUser.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
            HttpServletResponse response, FilterChain chain, Authentication authResult) 
            throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        String jwt = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt);

    }
        
}
