package com.example.demo.dao;

import com.example.demo.type.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private List<User> users = Arrays.asList(
            new User(1, "user1", 1), // Group A
            new User(2, "user2", 1), // Group A
            new User(3, "user3", 1), // Group A
            new User(4, "user4", 2), // Group B
            new User(5, "user5", 2)  // Group B
    );

    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

}
