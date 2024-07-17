package com.api.url_shortener.repository;

import com.api.url_shortener.model.Url;
import com.api.url_shortener.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
    List<Url> findByExpiresAtBefore(LocalDateTime dateTime);

    Page<Url> findByUser(User user, Pageable pageable);
}
