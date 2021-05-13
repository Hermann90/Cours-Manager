/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author hermann90
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // ---- Authorize all domains to access on this back-end (CORS) ---
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, Content-Type,"
                + "Access-Control-Request-Method,"
                + "Access-Control-Request-Headers,"
                + "Authorization");

        //--- Expose Headers on angular project -----
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
                + "Access-Control-Allow-Credentials, Authorization");

        //--- authorize OPTIONS mothod and set status to OK ----
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // --- get the token from request ----
            String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
            if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            System.out.println("++++++++++++++++++++++++++");

            System.out.println("token : " + jwt);

            System.out.println("++++++++++++++++++++++++++");

            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();

            String userName = claims.getSubject(); // ---- remember that Subject contains userName ---------

            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.get("authority")));
            });

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userName, null, authorities);

            // --------- Load security context of spring -----
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }

    }

}
