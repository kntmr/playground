package com.example.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class EchoController {

    @CrossOrigin
    @PostMapping("/echo")
    public Response echo(@RequestBody final Request request) {
        final long num = request.content;
        if (num % 3 == 0
                || String.valueOf(num).contains("3")) {
            return new Response("＼" + String.valueOf(num) + "／");
        }
        return new Response(String.valueOf(num));
    }

    static final class Request {
        public final Long content;
        public Request(@JsonProperty("content") Long content) {
            this.content = Objects.requireNonNull(content);
        }
    }

    static final class Response {
        public final String content;
        public Response(final String content) {
            this.content = Objects.requireNonNull(content);
        }
    }

}
