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

    @GetMapping("/r/{code}")
    public void redirect(
            HttpServletResponse httpServletResponse,
            @PathVariable("code") String code
    ) throws IOException {
        urlService.redirect(code, httpServletResponse);
    }

    @DeleteMapping("/{urlId}")
    public ResponseEntity<String> delete(@PathVariable("urlId") Long urlId) {
        urlService.delete(urlId);
        return ResponseEntity.ok("Shortened URL removed successfully");
    }

}
