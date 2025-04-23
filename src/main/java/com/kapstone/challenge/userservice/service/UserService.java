package com.kapstone.challenge.userservice.service;

import com.kapstone.challenge.userservice.dto.UserRequestDTO;
import com.kapstone.challenge.userservice.dto.response.ApiResponseDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public interface UserService {
    ApiResponseDTO<?> createUsers(@Valid List<UserRequestDTO> users);
}
