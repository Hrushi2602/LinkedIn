package com.LinkedInApp.connection_service.controller;

import com.LinkedInApp.connection_service.entity.Person;
import com.LinkedInApp.connection_service.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId){
        return ResponseEntity.ok(connectionService.getFirstDegreeConnections(userId));
    }
}
