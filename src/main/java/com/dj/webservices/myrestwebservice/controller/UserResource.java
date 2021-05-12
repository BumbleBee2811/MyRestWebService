package com.dj.webservices.myrestwebservice.controller;

import com.dj.webservices.myrestwebservice.bean.User;
import com.dj.webservices.myrestwebservice.dao.UserDAO;
import com.dj.webservices.myrestwebservice.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userDAO.findOne(id);
        if (null == user) {
            throw new UserNotFoundException("User having id = "+id+" was not found. Please verify the id you have entered.");
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> getUser(@RequestBody User user) {
        User savedUser = userDAO.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
