package com.hrushi.linkedin.user_service.controller;

import com.hrushi.linkedin.user_service.dto.LoginRequestDto;
import com.hrushi.linkedin.user_service.dto.SignUpRequestDto;
import com.hrushi.linkedin.user_service.dto.UserDto;
import com.hrushi.linkedin.user_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_TOPIC;

    @PostMapping("/message/{message}")
    public ResponseEntity<String> signUp(@PathVariable String message){

        for(int i =0 ; i < 100; i++ ){
        kafkaTemplate.send(KAFKA_RANDOM_TOPIC, message +i);
        }
        
        return ResponseEntity.ok("meaasge is qaued");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){

        UserDto userDto = authService.signUp(signUpRequestDto);

        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){

        String token = authService.login(loginRequestDto);

        return  ResponseEntity.ok(token);
    }

}
