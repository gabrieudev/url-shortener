package com.project.urlshortener.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ShortenUrlRequest (@NotBlank(message = "URL mandatory") String url) {
}
