package com.api.url_shortener.service;

import com.api.url_shortener.controller.dto.CountResponse;
import com.api.url_shortener.controller.dto.CustomizedUrlRequest;
import com.api.url_shortener.controller.dto.UrlRequest;
import com.api.url_shortener.controller.dto.UrlResponse;
import com.api.url_shortener.exception.EntityNotFoundException;
import com.api.url_shortener.exception.UrlAlreadyExistsException;
import com.api.url_shortener.model.SubscriptionPlan;
import com.api.url_shortener.model.Url;
import com.api.url_shortener.model.User;
import com.api.url_shortener.model.UserSubscription;
import com.api.url_shortener.repository.UrlRepository;
import com.api.url_shortener.repository.UserRepository;
import com.api.url_shortener.repository.UserSubscriptionRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    public UrlResponse shorten(UrlRequest urlRequest, Jwt jwt) {
        String shortenedUrl = "http://localhost:8080/r/";
        Url url = new Url();
        String token;

        User user = userRepository.findById(UUID.fromString(jwt.getSubject())).orElseThrow();
        Optional<UserSubscription> userSubscriptionOptional = userSubscriptionRepository.findByUserAndEndDateAfter(user, LocalDateTime.now());

        LocalDateTime expiresAt = userSubscriptionOptional.isEmpty() ? LocalDateTime.now().plusSeconds(86400) : null;

        do {
            token = RandomStringUtils.randomAlphanumeric(5);
        } while (urlRepository.existsById(token));

        url.setUser(user);
        url.setFullUrl(urlRequest.getFullUrl());
        url.setCount(0);
        url.setToken(token);
        url.setExpiresAt(expiresAt);

        urlRepository.save(url);
        return new UrlResponse(shortenedUrl + token);
    }

    public UrlResponse shortenWithCustomization(CustomizedUrlRequest customizedUrlRequest, Jwt jwt) {
        if (urlRepository.existsById(customizedUrlRequest.getToken())) {
            throw new UrlAlreadyExistsException("URL with this token already exists: " + customizedUrlRequest.getToken());
        }

        User user = userRepository.findById(UUID.fromString(jwt.getSubject())).orElseThrow();
        Optional<UserSubscription> userSubscriptionOptional = userSubscriptionRepository.findByUserAndEndDateAfter(user, LocalDateTime.now());

        LocalDateTime expiresAt = userSubscriptionOptional.isEmpty() ? LocalDateTime.now().plusSeconds(86400) : null;

        Url url = new Url(
                customizedUrlRequest.getToken(),
                user,
                customizedUrlRequest.getFullUrl(),
                expiresAt,
                0
        );

        String shortenedUrl = "http://localhost:8080/r/" + customizedUrlRequest.getToken();
        urlRepository.save(url);
        return new UrlResponse(shortenedUrl);
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

    public void checkAndDelete(LocalDateTime dateTime) {
        List<Url> urls = urlRepository.findByExpiresAtBefore(dateTime);
        urls.forEach(
                url -> urlRepository.delete(url)
        );
    }

}
