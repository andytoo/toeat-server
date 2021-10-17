package com.toeat.api.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Optional;

@Configuration
@PropertySource("app.properties")
public class AppConfig {

    @Autowired
    private Environment env;

//    @Bean
//    public Hashids hashids() {
//        return new Hashids(env.getProperty("toeat.hash.salt"),8);
//    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
