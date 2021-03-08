package com.todolist.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.todolist.challenge.model.User;
import com.todolist.challenge.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * this function adds three default users to the database,
 */
@Component
public class UserInitializer {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void postConstruct() {

            User user1 = new User();
            user1.setId(1);
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("pass"));
            user1.setActive(true);
            user1.setRoles("ROLE_USER");
            userRepository.save(user1);

            User user2 = new User();
            user2.setId(2);
            user2.setUsername("user2");
            user2.setPassword(passwordEncoder.encode("pass"));
            user2.setActive(true);
            user2.setRoles("ROLE_USER");
            userRepository.save(user2);

            User user3 = new User();
            user3.setId(3);
            user3.setUsername("user3");
            user3.setPassword(passwordEncoder.encode("pass"));
            user3.setActive(true);
            user3.setRoles("ROLE_USER");
            userRepository.save(user3);
    }
}
