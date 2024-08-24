package com.api.url_shortener.controller.dto;

import com.api.url_shortener.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class RegisterDTO {
        @NotBlank
        private String name;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;

        public User toUser() {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(this, User.class);
        }

}
