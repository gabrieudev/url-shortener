package com.api.url_shortener.controller;

import com.api.url_shortener.controller.dto.SubscriptionPlanDTO;
import com.api.url_shortener.service.UserSubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user-subscriptions")
public class UserSubscriptionController {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Operation(
            summary = "Change subscription plan",
            description = "Endpoint that changes a user's subscription plan",
            tags = "User subscription plan"
    )
    @PostMapping("/change")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<String> changeSubscriptionPlan(
            @RequestParam("subscriptionPlanId") Long subscriptionPlanId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        userSubscriptionService.changePlan(subscriptionPlanId, jwt);
        return ResponseEntity.ok("Subscription plan changed successfully");
    }

    @Operation(
            summary = "Get subscription plan",
            description = "Endpoint that gets the user's subscription plan",
            tags = "User subscription plan"
    )
    @GetMapping("/subscription-plan")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public ResponseEntity<SubscriptionPlanDTO> getSubscriptionPlan(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(userSubscriptionService.getSubscriptionPlan(jwt));
    }

}
