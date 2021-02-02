package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String users(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users")
    public String newUser(@RequestParam("username") String name, @RequestParam("password") String rawPassword, @RequestParam("role") String roleName) {
        Role role = roleRepository.findByName(roleName);
        User user = new User(name, passwordEncoder.encode(rawPassword));
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
