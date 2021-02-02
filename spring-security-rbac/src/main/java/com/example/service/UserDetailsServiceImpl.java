package com.example.service;

import com.example.model.Permission;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(
                        Stream.concat(
                                Stream.of(user.getRole().nameWithPrefix()),
                                user.getRole().getPermissions().stream().map(Permission::getName)).toArray(String[]::new)));
    }

}
