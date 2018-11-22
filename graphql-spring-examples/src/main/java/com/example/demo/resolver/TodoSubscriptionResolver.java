package com.example.demo.resolver;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.example.demo.processor.TodoEmitterProcessor;
import com.example.demo.type.ToDo;

@Component
public class TodoSubscriptionResolver implements GraphQLSubscriptionResolver {

	private TodoEmitterProcessor processor;

	public TodoSubscriptionResolver(TodoEmitterProcessor processor) {
		this.processor = processor;
	}

	public Publisher<ToDo> added() {
		return processor.getProcessor().log();
	}

}
