package com.api.url_shortener.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum SubscriptionPlanEnum {
    PREMIUM(1L, "premium", "Shortened URLs do not expire over time", new BigDecimal("19.90"));

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
