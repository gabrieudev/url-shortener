package com.api.url_shortener.service;

import com.api.url_shortener.dto.CountResponse;
import com.api.url_shortener.dto.UrlRequest;
import com.api.url_shortener.dto.UrlResponse;
import com.api.url_shortener.exception.EntityNotFoundException;
import com.api.url_shortener.model.Url;
import com.api.url_shortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public UrlResponse shorten(UrlRequest urlRequest) {
        String shortenedUrl = "http://localhost:8080/r/";
        String token;
        Url url = new Url();
        url.setFullUrl(urlRequest.getFullUrl());
        url.setCount(0);
        do {
            token = RandomStringUtils.randomAlphanumeric(5);
        } while (urlRepository.existsById(token));
        url.setToken(token);
        url.setExpiresAt(Instant.now().plusSeconds(86400));
        urlRepository.save(url);
        return new UrlResponse(shortenedUrl + token, url.getExpiresAt());
    }

    public void delete(String token) {
        Url url = urlRepository.findById(token).orElseThrow(
                () -> new EntityNotFoundException("URL not found with this token: " + token)
        );
        urlRepository.delete(url);
    }

    public void redirect(String token, HttpServletResponse httpServletResponse) {
        Url url = urlRepository.findById(token).orElseThrow(
                () -> new EntityNotFoundException("URL not found")
        );
        String fullUrl = url.getFullUrl();
        url.setCount(url.getCount()+1);
        urlRepository.save(url);
        try {
            httpServletResponse.sendRedirect(fullUrl);
        } catch (Exception e) {
            log.error("error redirecting to URl [{}]: [{}]", fullUrl, e.getLocalizedMessage());
        }
    }

    public CountResponse getCount(String token) {
        Url url = urlRepository.findById(token).orElseThrow(
                () -> new EntityNotFoundException("URL not found")
        );
        return new CountResponse(url.getCount());
    }

    public void checkAndDelete(Instant dateTime) {
        List<Url> urls = urlRepository.findByExpiresAtBefore(dateTime);
        urls.forEach(
                url -> urlRepository.delete(url)
        );
    }

}
