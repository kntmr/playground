package com.example.demo.controller;

import java.time.Duration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import reactor.core.publisher.Flux;

@Controller
public class ViewController {

	@GetMapping("/view")
	public String index(Model model) {
		Flux<User> users = Flux.range(0, 10)
				.map(i -> new User("" + i, "name-" + i))
				.delayElements(Duration.ofMillis(500L));
		model.addAttribute("users", new ReactiveDataDriverContextVariable(users, 1));
		return "index";
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
