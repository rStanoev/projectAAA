package com.example.projecta.config;

import com.example.projecta.interceptor.UpdateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UpdateInterceptorConfig implements WebMvcConfigurer {

    private final UpdateInterceptor updateInterceptor;

    public UpdateInterceptorConfig(UpdateInterceptor updateInterceptor) {
        this.updateInterceptor = updateInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(updateInterceptor);
    }
}
