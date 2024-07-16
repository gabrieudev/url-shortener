package com.api.url_shortener.repository;

import com.api.url_shortener.model.User;
import com.api.url_shortener.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    Optional<UserSubscription> findByUserAndEndDateAfter(User user, LocalDateTime dateTime);
}
