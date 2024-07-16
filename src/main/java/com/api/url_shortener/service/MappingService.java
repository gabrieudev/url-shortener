package com.api.url_shortener.service;

import com.api.url_shortener.controller.dto.RegisterDTO;
import com.api.url_shortener.controller.dto.UserDTO;
import com.api.url_shortener.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User toModel(RegisterDTO registerDTO) {
        return modelMapper.map(registerDTO, User.class);
    }

}
