package com.example.demo.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.dao.TodoDao;
import com.example.demo.type.ToDo;
import com.example.demo.type._ToDo;

@Component
public class TodoMutationResolver implements GraphQLMutationResolver {

	private TodoDao todoDao;
	
	public TodoMutationResolver(TodoDao todoDao) {
		this.todoDao = todoDao;
	}
	
	public List<ToDo> add(_ToDo _todo) {
		List<ToDo> current = todoDao.findAll();
		return todoDao.add(new ToDo(current.size(), _todo.getContent(), _todo.isCompleted(), _todo.getUserId()));
	}

}
