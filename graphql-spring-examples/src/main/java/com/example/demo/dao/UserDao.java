package com.example.demo.dao;

import com.example.demo.type.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private List<User> users = Arrays.asList(
            new User(1, "user a1", 1),
            new User(2, "user a2", 1),
            new User(3, "user a3", 1),
            new User(4, "user b1", 2),
            new User(5, "user b2", 2)
    );

    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

}
