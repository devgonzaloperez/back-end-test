package com.gperez.back_end_test.adapters.out.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${product.api.base.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
            .baseUrl(baseUrl)
            .defaultHeader("Accept", "application/json")
            .build();
    }

};
