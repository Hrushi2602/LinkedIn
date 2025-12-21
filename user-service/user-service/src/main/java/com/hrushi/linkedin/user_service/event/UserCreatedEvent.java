package com.hrushi.linkedin.user_service.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
}
