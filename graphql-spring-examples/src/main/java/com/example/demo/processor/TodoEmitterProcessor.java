package com.example.demo.processor;

import org.springframework.stereotype.Component;

import com.example.demo.type.ToDo;

import reactor.core.publisher.EmitterProcessor;

@Component
public class TodoEmitterProcessor {

	private EmitterProcessor<ToDo> processor;

	public TodoEmitterProcessor() {
		processor = EmitterProcessor.create();
	}

	public EmitterProcessor<ToDo> getProcessor() {
		return processor;
	}

	public void sendMessage(ToDo todo) {
		processor.onNext(todo);
	}

}
