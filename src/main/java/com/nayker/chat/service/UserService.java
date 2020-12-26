package com.nayker.chat.service;

import com.nayker.chat.dto.User;

public interface UserService {
     User addUser(String username, String email, String password);
}
