package com.api.url_shortener.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizedUrlRequest {
    @NotBlank
    private String fullUrl;

    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The token must contain only letters and numbers")
    private String token;

}
