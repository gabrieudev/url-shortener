package com.api.url_shortener.repository;

import com.api.url_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    boolean existsByShortenedUrl(String shortenedUrl);
}
