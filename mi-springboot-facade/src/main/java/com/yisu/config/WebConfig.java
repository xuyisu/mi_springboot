package com.yisu.config;

import com.yisu.config.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/cart/**","/order/**","/address/**","/user/getUser");
    }
    @Bean
    public TokenInterceptor jwtInterceptor() {
        return new TokenInterceptor();
    }
}
