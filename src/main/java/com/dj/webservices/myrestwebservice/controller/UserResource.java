package com.dj.webservices.myrestwebservice.controller;

import com.dj.webservices.myrestwebservice.bean.User;
import com.dj.webservices.myrestwebservice.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userDAO.findOne(id);
    }

    @PostMapping("/users")
    public User getUser(@RequestBody User user) {
        return userDAO.save(user);
    }
}
