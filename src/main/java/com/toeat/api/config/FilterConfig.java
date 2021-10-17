package com.toeat.api.config;

import com.toeat.api.filter.JWTAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean<JWTAuthenticationFilter> filterRegistrationBean() {
//        FilterRegistrationBean<JWTAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
//        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
//        registrationBean.setFilter(jwtAuthenticationFilter);
//        registrationBean.addUrlPatterns("/api/*");
//        return registrationBean;
//    }
}
