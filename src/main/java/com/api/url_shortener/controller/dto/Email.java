package com.api.url_shortener.controller.dto;

public record Email(
        String to,
        String subject,
        String body
) {
}
