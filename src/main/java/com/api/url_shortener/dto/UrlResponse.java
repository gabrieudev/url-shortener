package com.api.url_shortener.dto;

import java.time.Instant;

public record UrlResponse(
        String shortenedUrl,
        Instant expiresAt
) {
}
