package com.project.urlshortener.web.controller;

import com.project.urlshortener.UrlService;
import com.project.urlshortener.web.dto.ShortenUrlRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/shorten-url")
    public ResponseEntity<Object> save(@Valid @RequestBody ShortenUrlRequest shortenUrlRequest, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlService.save(shortenUrlRequest, httpServletRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getUrl(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.FOUND).headers(urlService.redirect(id)).build();
    }

}
