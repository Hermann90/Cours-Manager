/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.dao;

import com.hermann90.CoursManager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hermann90
 */
public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
