package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.dao.UserDao;
import com.example.demo.type.User;
import org.springframework.stereotype.Component;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    private UserDao userDao;

    public UserQueryResolver(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(int id) throws Exception {
        return userDao.findById(id).orElseThrow(() -> new Exception("User not found."));
    }

}
