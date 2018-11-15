package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.type.ToDo;

@Component
public class TodoDao {

    private List<ToDo> todos = Arrays.asList(
            new ToDo(1, "user a1 task1", false, 1),
            new ToDo(2, "user a1 task2", false, 1),
            new ToDo(3, "user a1 task3", false, 1),
            new ToDo(4, "user a2 task1", false, 2),
            new ToDo(5, "user a2 task2", false, 2),
            new ToDo(6, "user a3 task1", false, 3),
            new ToDo(7, "user b1 task1", false, 4)
    );

    public List<ToDo> findByUser(int id) {
        return todos.stream().filter(t -> t.getUserId() == id).collect(Collectors.toList());
    }

    public List<ToDo> add(ToDo todo) {
    	List<ToDo> _todos = new ArrayList<>(todos);
    	_todos.add(todo);
    	todos = Arrays.asList(_todos.toArray(new ToDo[_todos.size()]));
    	return findByUser(todo.getUserId());
    }

}
