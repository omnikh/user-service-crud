package com.kapstone.challenge.userservice.service.impl;

import com.kapstone.challenge.userservice.dto.UserRequestDTO;
import com.kapstone.challenge.userservice.dto.response.ApiResponseDTO;
import com.kapstone.challenge.userservice.entity.User;
import com.kapstone.challenge.userservice.exception.UserAlreadyExistsException;
import com.kapstone.challenge.userservice.exception.UserNotFoundException;
import com.kapstone.challenge.userservice.repository.UserRepository;
import com.kapstone.challenge.userservice.service.UserService;
import com.kapstone.challenge.userservice.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class UserSerivceImpl implements UserService {

    @Autowired
    private ValidationUtils validationUtils;

    private UserRepository userRepository;

    @Override
    public ApiResponseDTO<?> createUsers(UserRequestDTO userCreationRequest) {
        try {
                 validationUtils.validateDto(userCreationRequest);
                 if(userRepository.existsByEmail(userCreationRequest.getEmail()) || userRepository.existsByName(userCreationRequest.getName())){
                     throw new UserAlreadyExistsException("User already exists by phoneNumber or email");
                 }

                 String encodedPassword = Base64.getEncoder().encodeToString(userCreationRequest.getPassword().getBytes());
                User newUser = new User(null,
                    userCreationRequest.getName(),
                    encodedPassword,
                    userCreationRequest.getEmail(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    true);
                 userRepository.save(newUser);
                 return new ApiResponseDTO<>(HttpStatus.CREATED,"User created successfully",false);
        } catch (ConstraintViolationException cve){
            return new ApiResponseDTO<>( HttpStatus.BAD_REQUEST, cve.getMessage(), true);
        } catch (Exception e) {
            return new ApiResponseDTO<>( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), true);
        }
    }

    @Override
    public ApiResponseDTO<?> getAllUsers() {
        try {
            return new ApiResponseDTO<User>(null,userRepository.findAll(),HttpStatus.CREATED,"Users fetched successfully",false);
        }catch (Exception e) {
            return new ApiResponseDTO<>( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), true);
        }
    }

    @Override
    public ApiResponseDTO<?> getUserById(Long userId) {
        try {
            User userFromDb = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No user exists for the given userId"));
            return new ApiResponseDTO<User>(userFromDb,null,HttpStatus.CREATED,"User found successfully",false);
        }catch (UserNotFoundException e){
            return new ApiResponseDTO<>( HttpStatus.NOT_FOUND, e.getMessage(), true);
        } catch (Exception e) {
            return new ApiResponseDTO<>( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), true);
        }
    }

    @Override
    public ApiResponseDTO<?> deleteUserById(Long userId) {
        try {
            boolean userExists = userRepository.existsById(userId);
            if(!userExists){
                throw new UserNotFoundException("No user exists for the given userId");
            }
            userRepository.deleteById(userId);
            return new ApiResponseDTO<User>(null,null,HttpStatus.OK,"User Deleted successfully",false);
        }catch (UserNotFoundException e){
            return new ApiResponseDTO<>( HttpStatus.NOT_FOUND, e.getMessage(), true);
        } catch (Exception e) {
            return new ApiResponseDTO<>( HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), true);
        }
    }
}
