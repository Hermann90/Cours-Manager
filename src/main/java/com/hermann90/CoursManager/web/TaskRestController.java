/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.web;

import com.hermann90.CoursManager.dao.TaskRepository;
import com.hermann90.CoursManager.entities.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hermann90
 */

@RestController
public class TaskRestController {
   
    @Autowired  // dependancy injection
    private TaskRepository taskRepository;
    
    /**
     * This controller get all tasks create on database
     * @return 
     */
    @GetMapping(value = "/tasks")
    public List<Task> listTasks(){
        return taskRepository.findAll();
    }
    
    /**
     * This controller save one task
     * @RequestBody is an annotation that inform to controller find the data on the Body of this request
     * @param task
     * @return 
     */
    @PostMapping(value = "/tasks")
    public Task saveTask(@RequestBody Task task){
        return taskRepository.save(task);
    }
    
}
