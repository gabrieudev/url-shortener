package com.api.url_shortener.config.data_loader;

import com.api.url_shortener.model.Role;
import com.api.url_shortener.model.SubscriptionPlan;
import com.api.url_shortener.model.User;
import com.api.url_shortener.model.enums.RoleEnum;
import com.api.url_shortener.model.enums.SubscriptionPlanEnum;
import com.api.url_shortener.repository.RoleRepository;
import com.api.url_shortener.repository.SubscriptionPlanRepository;
import com.api.url_shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@Transactional
public class AdminDataLoader implements CommandLineRunner {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RoleEnum.values()).map(
                roleEnum -> new Role(roleEnum.getId(), roleEnum.getRole())
        ).forEach(roleRepository::save);

        Arrays.stream(SubscriptionPlanEnum.values()).map(
                subscriptionPlanEnum -> new SubscriptionPlan(
                        subscriptionPlanEnum.getId(),
                        subscriptionPlanEnum.getName(),
                        subscriptionPlanEnum.getDescription(),
                        subscriptionPlanEnum.getPrice()
                )
        ).forEach(subscriptionPlanRepository::save);

        Optional<User> userOptional = userRepository.findByEmail(adminEmail);
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setEmail(adminEmail);
            user.setPassword(bCryptPasswordEncoder.encode(adminPassword));
            Set<Role> roles = new HashSet<>(roleRepository.findAll());
            user.setRoles(roles);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setName("admin");
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}
