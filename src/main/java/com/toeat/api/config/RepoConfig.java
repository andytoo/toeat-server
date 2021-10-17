package com.toeat.api.config;

import com.toeat.api.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.UUID;

@Configuration
public class RepoConfig {
//    @Bean
//    public ApplicationListener<BeforeSaveEvent> userRestaurantIdSetting() {
//        return event -> {
//            if (event.getEntity() instanceof User) {
//                User user = (User) event.getEntity();
//                if (user.getRestaurantId() == null) {
//                    user.setRestaurantId(UUID.randomUUID());
//                }
//            }
//        };
//    }
}
