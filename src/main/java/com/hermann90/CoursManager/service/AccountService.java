/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.service;

import com.hermann90.CoursManager.entities.AppRole;
import com.hermann90.CoursManager.entities.AppUser;

/**
 *
 * @author hermann90
 */
public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String userName, String roleName);
    public AppUser findUserByUserName(String userName);
}
