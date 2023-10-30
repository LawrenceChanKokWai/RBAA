package com.kokwai.service.implementation;

import com.kokwai.dto.UserDTO;
import com.kokwai.dtoMapper.UserDTOMapper;
import com.kokwai.model.User;
import com.kokwai.repository.UserRepository;
import com.kokwai.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
