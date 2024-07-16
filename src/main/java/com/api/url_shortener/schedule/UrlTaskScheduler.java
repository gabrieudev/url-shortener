package com.api.url_shortener.schedule;

import com.api.url_shortener.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UrlTaskScheduler {

    @Autowired
    private UrlService urlService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void delete() {
        LocalDateTime now = LocalDateTime.now();
        urlService.checkAndDelete(now);
        log.info("running at {}", now.toString());
    }

}
