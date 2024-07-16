package com.api.url_shortener.controller.dto;

import java.time.Instant;

public record TokenDTO(
        String token,
        Instant expiresAt
) {
}
