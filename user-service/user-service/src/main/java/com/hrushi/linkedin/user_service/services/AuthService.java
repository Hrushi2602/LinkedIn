package com.hrushi.linkedin.user_service.services;

import com.hrushi.linkedin.user_service.Utils.passwordUtils;
import com.hrushi.linkedin.user_service.dto.LoginRequestDto;
import com.hrushi.linkedin.user_service.dto.SignUpRequestDto;
import com.hrushi.linkedin.user_service.dto.UserDto;
import com.hrushi.linkedin.user_service.entity.User;
import com.hrushi.linkedin.user_service.exception.exception.BadRequestException;
import com.hrushi.linkedin.user_service.exception.exception.ResourceNotFoundException;
import com.hrushi.linkedin.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public UserDto signUp(SignUpRequestDto signUpRequestDto) {

        boolean exists = userRepository.existsByEmail(signUpRequestDto.getEmail());

        if (exists){
            throw  new BadRequestException("user Already exits");
        }
        User user = modelMapper.map(signUpRequestDto, User.class);
        user.setPassword(passwordUtils.hashPassword(signUpRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with this email: "+ loginRequestDto.getEmail()));

        boolean isPasswordMatch = passwordUtils.checkPassword(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordMatch) {
           throw new BadRequestException("Incorrect Password");
        }

        return  jwtService.generateAccessToken(user);
    }
}
