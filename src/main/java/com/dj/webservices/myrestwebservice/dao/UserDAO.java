package com.dj.webservices.myrestwebservice.dao;

import com.dj.webservices.myrestwebservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDAO {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Dibyajyoti", new Date()));
        users.add(new User(2, "Suraj", new Date()));
        users.add(new User(3, "Sambit", new Date()));
        users.add(new User(4, "Ajit", new Date()));
        users.add(new User(5, "Subrat", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User save (User user) {
        if (user.getId() == null) {
            user.setId(users.get(users.size()-1).getId() + 1);
        }
        users.add(user);
        return user;
    }

    public User removeById(int id) {
        for (User user : users) {
            if (id == user.getId()) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }
}
