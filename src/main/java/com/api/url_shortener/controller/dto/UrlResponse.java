package com.api.url_shortener.controller.dto;

import java.time.Instant;

public record UrlResponse(
        String shortenedUrl,
        Instant expiresAt
) {
}
