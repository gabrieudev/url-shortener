package com.api.url_shortener.service;

import com.api.url_shortener.dto.UrlRequest;
import com.api.url_shortener.dto.UrlResponse;
import com.api.url_shortener.exception.EntityNotFoundException;
import com.api.url_shortener.model.Url;
import com.api.url_shortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;

@Service
public class UrlService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UrlRepository urlRepository;

    public void shorten(UrlRequest urlRequest) {
        Url url = modelMapper.map(urlRequest, Url.class);
        StringBuilder shortenedUrl = new StringBuilder("http://localhost:8080/r/");
        do {
            shortenedUrl.append(RandomStringUtils.randomAlphanumeric(5));
        } while (urlRepository.existsByShortenedUrl(shortenedUrl.toString()));
        url.setShortenedUrl(shortenedUrl.toString());
        url.setExpiresAt(Instant.now().plusSeconds(3600));
        urlRepository.save(url);
    }

    public void delete(Long id) {
        Url url = urlRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Url not found with this id: " + id)
        );
        urlRepository.delete(url);
    }

    public void redirect(String shortenedUrl, HttpServletResponse httpServletResponse) throws IOException {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl).orElseThrow(
                () -> new EntityNotFoundException("Url not found")
        );
        String fullUrl = url.getFullUrl();
        httpServletResponse.sendRedirect(fullUrl);
    }

}
