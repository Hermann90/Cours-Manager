/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.impl;

import com.hermann90.CoursManager.dao.RoleRepository;
import com.hermann90.CoursManager.dao.UserRepository;
import com.hermann90.CoursManager.entities.AppRole;
import com.hermann90.CoursManager.entities.AppUser;
import com.hermann90.CoursManager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hermann90
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public AppUser saveUser(AppUser user) {
        String hashPWD = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPWD);
        
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        // Call repository to save role
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        // --- recover role and user by roleName and userName
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByUserName(userName);
        
        user.getRoles().add(role);
        

    }

    @Override
    public AppUser findUserByUserName(String userName) {
        
        return userRepository.findByUserName(userName);
    }
    
}
