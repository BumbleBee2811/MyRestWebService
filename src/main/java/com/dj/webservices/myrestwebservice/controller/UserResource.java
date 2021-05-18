package com.dj.webservices.myrestwebservice.controller;

import com.dj.webservices.myrestwebservice.bean.User;
import com.dj.webservices.myrestwebservice.dao.UserDAO;
import com.dj.webservices.myrestwebservice.exceptions.UserNotFoundException;
import com.dj.webservices.myrestwebservice.response.UserErrorResponse;
import com.dj.webservices.myrestwebservice.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResource {

    @Autowired
    private MessageSource messageSource;

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
    public ResponseEntity<Object> getUser(@Valid @RequestBody User user) {
        User savedUser = userDAO.save(user);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        if (null != savedUser) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUser(savedUser);
            userResponse.setMessage(HttpStatus.CREATED.getReasonPhrase());
            userResponse.setStatus(HttpStatus.CREATED.value());
            userResponse.setTimestamp(System.currentTimeMillis());
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }
        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setMessage(HttpStatus.CREATED.getReasonPhrase());
        userErrorResponse.setStatus(HttpStatus.CREATED.value());
        userErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User deletedUser = userDAO.removeById(id);
        if (null == deletedUser) {
            throw new UserNotFoundException("User having id = "+id+" was not found. Please verify the id you have entered.");
        }
        return deletedUser;
    }

//    @GetMapping("/hello-world-internationalized")
//    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message", null, locale);
//    }
    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
