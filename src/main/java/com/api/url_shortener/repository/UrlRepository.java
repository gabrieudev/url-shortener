package com.api.url_shortener.repository;

import com.api.url_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
    List<Url> findByExpiresAtBefore(Instant dateTime);
}
