package com.api.url_shortener.controller;

import com.api.url_shortener.dto.UrlRequest;
import com.api.url_shortener.dto.UrlResponse;
import com.api.url_shortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shorten(@Valid @RequestBody UrlRequest urlRequest) {
        return ResponseEntity.ok(urlService.shorten(urlRequest));
    }

    @GetMapping("/r/{token}")
    public void redirect(
            HttpServletResponse httpServletResponse,
            @PathVariable("token") String token
    ) {
        urlService.redirect(token, httpServletResponse);
    }

    @DeleteMapping("/{token}")
    public ResponseEntity<String> delete(@PathVariable("token") String token) {
        urlService.delete(token);
        return ResponseEntity.ok("Shortened URL removed successfully");
    }

}
