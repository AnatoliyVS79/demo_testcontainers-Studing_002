package com.example.demo_testcontainers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }

    public User loadUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public User createUser(String login, String email) {
        return userRepository.save(new User(
                null, login, email
        ));
    }

    public User editUser(UUID uuid, String login, String email) {
        final User currentUser = loadUserById(uuid);
        assert currentUser.getId() != null;
        return userRepository.save(new User(uuid, login, email));
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
