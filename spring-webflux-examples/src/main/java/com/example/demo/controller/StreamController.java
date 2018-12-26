package com.example.demo.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
public class StreamController {

	@ResponseBody
	@GetMapping(value = "/json", produces = MediaType.APPLICATION_STREAM_JSON_VALUE) // Accept: application/stream+json
	public Flux<User> index() {
		return Flux.range(0, 10)
				.map(i -> new User("" + i, "name-" + i))
				.delayElements(Duration.ofMillis(500L));
	}

	@ResponseBody
	@GetMapping(value = "/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // Accept: text/event-stream
	public Flux<User> index2() {
		return Flux.range(0, 10)
				.map(i -> new User("" + i, "name-" + i))
				.delayElements(Duration.ofMillis(500L));
	}

	class User {
		private String id;
		private String name;
		public User(String id, String name) {
			this.id = id;
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}

}
