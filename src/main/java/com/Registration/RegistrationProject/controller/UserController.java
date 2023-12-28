package com.Registration.RegistrationProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Registration.RegistrationProject.dao.UserDao;
import com.Registration.RegistrationProject.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDao userDao;
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // You should hash the password before saving it to the database (use bcrypt or similar)
    	System.out.println("user is="+user);
    	if(!userDao.existsByUsername(user.getUsername()))
    	{
    	  
    		userDao.save(user);
    		 return ResponseEntity.ok().body("Registration Successful");
    	}
    	
    	else {
    		return ResponseEntity.badRequest().body("User Already Exist");
    	}
    	
    
    }
    
    
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
    	System.out.println("logging method is calling....");
    	
        User existingUser = userDao.findByUsername(user.getUsername());
        

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
      
            return ResponseEntity.ok().body("Login Successful");
        } else {
        	return ResponseEntity.badRequest().body("Invalid credentials");
           
        }
    }
    
    
    @CrossOrigin
    @GetMapping("/viewProfile/{username}")
    
    public ResponseEntity<User> viewProfile(@PathVariable("username") String username) {
    	
    	System.out.println("username===>" +username);
    	
    	User existingUser = userDao.findByUsername(username);
    	
    	if(existingUser==null) {
    		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    	else {
    		return ResponseEntity.ok().body(existingUser);
    	}
    	
    	
    	
    }
    
    
    
    
    
    
    
    
    
}
