package com.kapstone.challenge.userservice.service.impl;

import com.kapstone.challenge.userservice.dto.UserRequestDTO;
import com.kapstone.challenge.userservice.dto.response.ApiResponseDTO;
import com.kapstone.challenge.userservice.exception.UserAlreadyExistsException;
import com.kapstone.challenge.userservice.repository.UserRepository;
import com.kapstone.challenge.userservice.service.UserService;
import com.kapstone.challenge.userservice.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Component
public class UserSerivceImpl implements UserService {

    @Autowired
    private ValidationUtils validationUtils;

    private UserRepository userRepository;

    @Override
    public ApiResponseDTO<?> createUsers(List<UserRequestDTO> users) {
        try {
            for(UserRequestDTO userCreationRequest : users){
                 validationUtils.validateDto(userCreationRequest);
                 if(userRepository.existsByEmail(userCreationRequest.getEmail()) || userRepository.existsByName(userCreationRequest.getName())){
                     throw new UserAlreadyExistsException("User already exists");
                 }

            }
        } catch (ConstraintViolationException cve){
            return new ApiResponseDTO<>( HttpStatus.BAD_REQUEST, cve.getMessage(), true);
        } catch (Exception e) {
            return new ApiResponseDTO<>( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), true);
        }
    }
}
