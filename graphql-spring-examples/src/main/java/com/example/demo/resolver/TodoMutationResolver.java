package com.example.demo.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.dao.TodoDao;
import com.example.demo.processor.TodoEmitterProcessor;
import com.example.demo.type.ToDo;
import com.example.demo.type._ToDo;

@Component
public class TodoMutationResolver implements GraphQLMutationResolver {

	private TodoDao todoDao;
	private TodoEmitterProcessor processor;

	public TodoMutationResolver(TodoDao todoDao, TodoEmitterProcessor processor) {
		this.todoDao = todoDao;
		this.processor = processor;
	}

	public List<ToDo> add(_ToDo _todo) {
		List<ToDo> current = todoDao.findAll();
		ToDo newTodo = new ToDo(current.size(), _todo.getContent(), _todo.isCompleted(), _todo.getUserId());
		todoDao.add(newTodo);
		processor.sendMessage(newTodo);
		return todoDao.findByUser(_todo.getUserId());
	}

}
