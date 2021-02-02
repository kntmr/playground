package com.example;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.PermissionRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		Permission readable = new Permission("READ");
		Permission writable = new Permission("WRITE");

		permissionRepository.saveAll(Arrays.asList(readable, writable));

		Role admin  = new Role("ADMIN");
		admin.setPermissions(Arrays.asList(readable, writable));
		Role guest  = new Role("GUEST");
		guest.setPermissions(Arrays.asList(readable));

		roleRepository.saveAll(Arrays.asList(admin, guest));

		User user1 = new User("user1", passwordEncoder.encode("password"));
		user1.setRole(admin);
		User user2 = new User("user2", passwordEncoder.encode("password"));
		user2.setRole(guest);

		userRepository.saveAll(Arrays.asList(user1, user2));
	}

}
