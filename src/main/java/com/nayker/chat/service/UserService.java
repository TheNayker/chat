package com.nayker.chat.service;

import com.nayker.chat.dto.User;

import java.util.List;

public interface UserService {
     List<User> getUsers();

     User addUsers(String username, String email, String password);
}
