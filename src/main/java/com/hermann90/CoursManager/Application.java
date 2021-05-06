package com.hermann90.CoursManager;

import com.hermann90.CoursManager.dao.TaskRepository;
import com.hermann90.CoursManager.entities.Task;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This implematation crate the tasks, when the application start
     * @param args
     * @throws Exception 
     */
    @Override
    public void run(String... args) throws Exception {
        
        //---------- after string start, we can creat these some Task object ---------- 
        Stream.of("MATH 111", "INF 121", "PHY 122").forEach(t->{
            taskRepository.save(new Task(null,t));
        });

        //---------- Display the tasks in the console ---------- 
        taskRepository.findAll().forEach(t->{
            System.out.println(t.getTaskName());
        });
    }

}
