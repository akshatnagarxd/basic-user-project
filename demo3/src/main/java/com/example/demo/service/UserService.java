package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public boolean saveUser(User user);
    public User getUserById(int id);
    public void deleteUserById(int id);
    public void updateUser(User user);
    public User getUserByEmail(String email);
    public boolean isUserExists(String email, String password);
}
