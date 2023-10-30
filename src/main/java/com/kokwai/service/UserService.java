package com.kokwai.service;

import com.kokwai.dto.UserDTO;
import com.kokwai.model.User;

public interface UserService {

    UserDTO createUser(User user);

}
