/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author hermann90
 */

@Entity
@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class AppUser implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true) // This annotation make username attribute unique
    private String userName;
    private String password;
    
    /*1- A user can have several roles and one role that concern several users
    2- fetch = FetchType.EAGER : This param allows that each time I load a user, his roles are also loaded */
    @ManyToMany(fetch = FetchType.EAGER) 
    private Collection<AppRole> roles = new ArrayList<>();
}
