package com.dj.webservices.myrestwebservice.controller;

import com.dj.webservices.myrestwebservice.bean.Post;
import com.dj.webservices.myrestwebservice.bean.User;
import com.dj.webservices.myrestwebservice.exceptions.UserNotFoundException;
import com.dj.webservices.myrestwebservice.repository.PostRepository;
import com.dj.webservices.myrestwebservice.repository.UserRepository;
import com.dj.webservices.myrestwebservice.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User having id = " + id + " was not found. Please verify the id you have entered.");
        }

        EntityModel<User> resource = EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> getUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(savedUser);
        userResponse.setMessage(HttpStatus.CREATED.getReasonPhrase());
        userResponse.setStatus(HttpStatus.CREATED.value());
        userResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User having id = " + id + " was not found. Please verify the id you have entered.");
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userFound = userRepository.findById(id);
        if (!userFound.isPresent()) {
            throw new UserNotFoundException("User having id = " + id + " was not found. Please verify the id you have entered.");
        }

        User user = userFound.get();

        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
