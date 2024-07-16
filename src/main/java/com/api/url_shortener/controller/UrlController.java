package com.api.url_shortener.controller;

import com.api.url_shortener.controller.dto.CountResponse;
import com.api.url_shortener.controller.dto.CustomizedUrlRequest;
import com.api.url_shortener.controller.dto.UrlRequest;
import com.api.url_shortener.controller.dto.UrlResponse;
import com.api.url_shortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Operation(
            summary = "Shorten URL",
            description = "Endpoint that shortens a URL and returns it with the expiration time",
            tags = "URL"
    )
    @PostMapping("/shorten")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<UrlResponse> shorten(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody UrlRequest urlRequest
    ) {
        return ResponseEntity.ok(urlService.shorten(urlRequest, jwt));
    }

    @Operation(
            summary = "Shorten with customization",
            description = "Endpoint that shortens a URL with a custom token",
            tags = "URL"
    )
    @PostMapping("/shorten/custom")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<UrlResponse> shortenWithCustomization(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CustomizedUrlRequest customizedUrlRequest
    ) {
        return ResponseEntity.ok(urlService.shortenWithCustomization(customizedUrlRequest, jwt));
    }

    @Operation(
            summary = "Redirect URL",
            description = "Endpoint that redirects a shortened URL to the full URL",
            tags = "URL"
    )
    @GetMapping("/r/{token}")
    public void redirect(
            HttpServletResponse httpServletResponse,
            @PathVariable("token") String token
    ) {
        urlService.redirect(token, httpServletResponse);
    }

    @Operation(
            summary = "Delete URL",
            description = "Endpoint that deletes a shortened URL",
            tags = "URL"
    )
    @DeleteMapping("/r/{token}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("token") String token) {
        urlService.delete(token);
        return ResponseEntity.ok("Shortened URL removed successfully");
    }

    @Operation(
            summary = "Get click count",
            description = "Endpoint that gets click count of a shortened URL",
            tags = "URL"
    )
    @GetMapping("/r/{token}/count")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<CountResponse> getCount(@PathVariable("token") String token) {
        return ResponseEntity.ok(urlService.getCount(token));
    }

}
