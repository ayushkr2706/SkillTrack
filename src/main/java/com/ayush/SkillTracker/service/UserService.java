package com.ayush.SkillTracker.service;

import com.ayush.SkillTracker.dto.LoginRequestDTO;
import com.ayush.SkillTracker.dto.LoginResponseDTO;
import com.ayush.SkillTracker.dto.RegisterRequestDTO;
import com.ayush.SkillTracker.dto.RegisterResponseDTO;
import com.ayush.SkillTracker.entity.User;
import com.ayush.SkillTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequest){
        Optional<User> fetchedUser = userRepository.findByUsername(loginRequest.getUsername());
        if(fetchedUser.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        User user = fetchedUser.get();

        if(!loginRequest.getUsername().equals(user.getUsername())
                && !loginRequest.getPassword().equals(user.getPassword())){

            LoginResponseDTO response = mapToLoginResponseDTO(loginRequest);
            response.setMessage("Invalid username or password");
            return response;
        }

        return mapToLoginResponseDTO(loginRequest);
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequest){

        User user = mapToRegisterUserEntity(registerRequest);
        userRepository.save(user);
        return mapToRegisterResponse(user);
    }

    private User mapToRegisterUserEntity(RegisterRequestDTO registerRequest) {

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setEmailId(registerRequest.getEmailId());
        user.setMobileNumber(registerRequest.getMobileNumber());
        user.setPassword(registerRequest.getPassword());
        return user;
    }

    public LoginResponseDTO mapToLoginResponseDTO(LoginRequestDTO loginRequest){

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setUserName(loginRequest.getUsername());
        loginResponse.setMessage("login successful");
        return loginResponse;
   }

   public RegisterResponseDTO mapToRegisterResponse(User user) {

       RegisterResponseDTO response = new RegisterResponseDTO();
       response.setUsername(user.getUsername());
       response.setMessage("User Registered Successfully");
       response.setCreatedAt(LocalDateTime.now());
       return response;
   }
}
