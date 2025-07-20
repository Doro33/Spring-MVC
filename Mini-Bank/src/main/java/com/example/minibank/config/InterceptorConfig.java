package com.example.minibank.config;

import com.example.minibank.interceptor.ApiKeyInterceptor;
import com.example.minibank.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("!test")
public class InterceptorConfig implements WebMvcConfigurer {

    private final ApiKeyInterceptor apiKeyInterceptor;
    private final LoggingInterceptor loggingInterceptor;

    @Autowired
    public InterceptorConfig(ApiKeyInterceptor apiKeyInterceptor, LoggingInterceptor loggingInterceptor) {
        this.apiKeyInterceptor = apiKeyInterceptor;
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(apiKeyInterceptor);
    }
}
