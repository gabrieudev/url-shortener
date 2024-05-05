package com.project.urlshortener;

import com.project.urlshortener.exception.ObjectNotFoundException;
import com.project.urlshortener.model.UrlModel;
import com.project.urlshortener.repository.UrlRepository;
import com.project.urlshortener.web.dto.ShortenUrlRequest;
import com.project.urlshortener.web.dto.ShortenUrlResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public ShortenUrlResponse save(ShortenUrlRequest shortenUrlRequest, HttpServletRequest httpServletRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));
        urlRepository.save(new UrlModel(id, shortenUrlRequest.url(), LocalDateTime.now().plusHours(1)));
        String redirectUrl = httpServletRequest.getRequestURL().toString().replace("shorten-url", id);
        return new ShortenUrlResponse(redirectUrl);
    }

    public HttpHeaders redirect(String id) {
        Optional<UrlModel> url = urlRepository.findById(id);
        url.orElseThrow(() -> new ObjectNotFoundException("URL not found"));
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setLocation(URI.create(url.get().getFullUrl()));
        return httpHeader;
    }

}
