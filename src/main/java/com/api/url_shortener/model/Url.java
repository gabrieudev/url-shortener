package com.api.url_shortener.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "tb_urls")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_url", nullable = false)
    private String fullUrl;

    @Column(name = "shortened_url", nullable = false, unique = true)
    private String shortenedUrl;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

}
