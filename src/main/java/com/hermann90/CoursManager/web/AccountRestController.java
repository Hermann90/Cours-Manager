/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.web;

import com.hermann90.CoursManager.entities.AppUser;
import com.hermann90.CoursManager.service.AccountService;
import com.hermann90.CoursManager.utils.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hermann90
 */

@RestController
public class AccountRestController {
    
    @Autowired
    private AccountService accountService;
    
    @PostMapping(value = "/register")
    public AppUser register(@RequestBody RegisterForm userForm){
        
        if(!userForm.getPassword().equals(userForm.getRePassword())){
            throw new RuntimeException("You most confirm your password !");
        }
        
        AppUser user = accountService.findUserByUserName(userForm.getUserName());
        if(user != null){
           throw new RuntimeException("This user already exists !"); 
        }
            
        AppUser appUser = new AppUser();
        appUser.setPassword(userForm.getPassword());
        appUser.setUserName(userForm.getUserName());
        
         accountService.saveUser(appUser);
         // add a default role when registering the user
         accountService.addRoleToUser(userForm.getUserName(), "USER");
         
         return appUser;
    }
    
}
