package com.LinkedInApp.connection_service.service;


import com.LinkedInApp.connection_service.entity.Person;
import com.LinkedInApp.connection_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConnectionService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections(Long userId){

        log.info("Getting first degree connections for userid" + userId);

//        return personRepository.getFirstDegreeConnections(userId);
        List<Person> connections = personRepository.getFirstDegreeConnections(userId);

        if (connections == null || connections.isEmpty()) {
            log.warn("No first-degree connections found for userId: {}", userId);
        } else {
            log.info("Found {} connections for userId {}", connections.size(), userId);
            connections.forEach(p ->
                    log.info("Connected Person -> userId: {}, name: {}",
                            p.getUserId(), p.getName())
            );
        }

        return connections;
    }
}
