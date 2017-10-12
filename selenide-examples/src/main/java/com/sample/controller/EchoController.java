package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EchoController {

    @GetMapping("/")
    String index() {
        return "index";
    }

    @PostMapping("echo")
    @ResponseBody
    String echo(@RequestParam String input) {
        return "＼" + input + "／";
    }

}
