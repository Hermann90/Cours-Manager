/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hermann90
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Task implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //id is generated with auto increment strategy
    private Long id;
    private String taskName;
    
}
