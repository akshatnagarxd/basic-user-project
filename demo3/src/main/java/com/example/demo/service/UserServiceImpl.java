package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public boolean saveUser(User user) {
        User savedUser = userRepository.save(user);
        // Check if the user was saved successfully
        if(savedUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
        // Optionally, you can check if the user was deleted successfully
        if (getUserById(id) != null) {
            throw new RuntimeException("User not deleted successfully");
        }
    }

    @Override
    public void updateUser(User user) {
        saveUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()) {
            return user.get();
        }
        // If user is not found, return null
        return null;
    }

    @Override
    public boolean isUserExists(String email, String password) {
        Optional user = Optional.ofNullable(getUserByEmail(email));
        if(user.isPresent()) {
            User existingUser = (User) user.get();
            // Check if the password matches
            if(existingUser.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
