package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.dao.TodoDao;
import com.example.demo.type.ToDo;
import com.example.demo.type.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoResolver implements GraphQLResolver<User> {

    private TodoDao todoDao;

    public TodoResolver(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public List<ToDo> todos(User user) {
        return todoDao.findByUser(user.getId());
    }

}
