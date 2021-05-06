package com.hermann90.CoursManager;

import com.hermann90.CoursManager.dao.TaskRepository;
import com.hermann90.CoursManager.entities.AppRole;
import com.hermann90.CoursManager.entities.AppUser;
import com.hermann90.CoursManager.entities.Task;
import com.hermann90.CoursManager.service.AccountService;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private AccountService accountService;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * when the application starts, all Bean annotations are executed. then the result 
     * becomes a Bean Spring which means that we can inject it everywhere
     * 
     * @return 
     */
    @Bean
    public BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
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
        
        //----------- save User -----------
        
        accountService.saveUser(new AppUser(null,"admin","12345",null));
        accountService.saveUser(new AppUser(null,"user","12345",null));
        
        accountService.saveRole(new AppRole(null,"ADMIN"));        
        accountService.saveRole(new AppRole(null,"USER"));
        
        accountService.addRoleToUser("admin", "ADMIN");
        accountService.addRoleToUser("admin", "USER");        
        accountService.addRoleToUser("user", "USER");



    }

}
