package com.talentotech.energia.controller;
import com.talentotech.energia.model.User;
import com.talentotech.energia.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository =userRepository;
    }
    @PostMapping
    public User create(@RequestBody User user) {
        
        return userRepository.save(user);
    }
    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }
    //READ BY ID
    @GetMapping("/{id}")
    public User findByID(@PathVariable Long id){
        return userRepository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Usuario no encontrado"));
    }
    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User userDetails){
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(userDetails.getUsername()!=null &&
        !userDetails.getUsername().trim().isEmpty()){
        user.setUsername(userDetails.getUsername());
        }
          if(userDetails.getEmail()!=null &&
        !userDetails.getEmail().trim().isEmpty()){
          user.setEmail(userDetails.getEmail());
        }
         if(userDetails.getPassword()!=null &&
        !userDetails.getPassword().trim().isEmpty()){
          user.setPassword(userDetails.getPassword());
        }
            
          user.setRole(userDetails.getRole());
        
        
        return userRepository.save(user);

    }
    
      
    
}
