package com.api.url_shortener.service;

import com.api.url_shortener.controller.dto.SubscriptionPlanDTO;
import com.api.url_shortener.exception.EntityNotFoundException;
import com.api.url_shortener.exception.UserSubscriptionAlreadyExistsException;
import com.api.url_shortener.model.SubscriptionPlan;
import com.api.url_shortener.model.User;
import com.api.url_shortener.model.UserSubscription;
import com.api.url_shortener.repository.SubscriptionPlanRepository;
import com.api.url_shortener.repository.UserRepository;
import com.api.url_shortener.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserSubscriptionService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private UserRepository userRepository;

    public void changePlan(Long subscriptionPlanId, Jwt jwt) {
        UserSubscription userSubscription = new UserSubscription();

        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId).orElseThrow(
                () -> new EntityNotFoundException("Subscription plan not found with this id: " + subscriptionPlanId)
        );
        User user = userRepository.findById(UUID.fromString(jwt.getSubject())).orElseThrow();

        if (userSubscriptionRepository.existsByUserAndEndDateAfter(user, LocalDateTime.now())) {
            throw new UserSubscriptionAlreadyExistsException("You already have a subscription plan");
        }

        userSubscription.setUser(user);
        userSubscription.setSubscriptionPlan(subscriptionPlan);
        userSubscription.setStartDate(LocalDateTime.now());
        userSubscription.setEndDate(LocalDateTime.now().plusMonths(1));
        userSubscriptionRepository.save(userSubscription);
    }

    public SubscriptionPlanDTO getSubscriptionPlan(Jwt jwt) {
        User user = userRepository.findById(UUID.fromString(jwt.getSubject())).orElseThrow();
        UserSubscription userSubscription = userSubscriptionRepository.findByUserAndEndDateAfter(user, LocalDateTime.now()).orElseThrow(
                () -> new EntityNotFoundException("You don't have a subscription plan")
        );
        return mappingService.toDto(userSubscription.getSubscriptionPlan());
    }

}
