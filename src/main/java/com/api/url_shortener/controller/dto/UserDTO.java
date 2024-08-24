package com.api.url_shortener.controller.dto;

import com.api.url_shortener.model.Role;
import com.api.url_shortener.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    private String name;

    private String email;

    private String password;

    private Set<Role> roles;

    private Instant createdAt;

    private Instant updatedAt;

    private boolean enabled;

    public User toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, User.class);
    }

}
