/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author hermann90
 */

@Configuration
@EnableWebSecurity // To Activate Web Security
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     *  This method is used to allow spring security to find users and roles
     * @param auth
     * @throws Exception 
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}12345").roles("ADMIN","USER")
                .and()
                .withUser("user").password("{noop}12345").roles("USER");
    }

    /**
     * This method is used to allow spring security to define the access rights.
     * Here we can decide to add filters.
     * @param http
     * @throws Exception 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*by default, spring security generates a synchronized token. it must be deactivated to 
        allow other applications to connect to the back-end*/
        http.csrf().disable();
        
        http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();
    }
    
    
}
