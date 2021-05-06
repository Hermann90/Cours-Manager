/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.impl;

import com.hermann90.CoursManager.entities.AppUser;
import com.hermann90.CoursManager.service.AccountService;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author hermann90
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser user = accountService.findUserByUserName(userName);
        
        if(user == null) try {
            throw new UserPrincipalNotFoundException(userName);
        } catch (UserPrincipalNotFoundException ex) {
            Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r ->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        
        return new User(user.getUserName(), user.getPassword(), authorities);
    }    
}
