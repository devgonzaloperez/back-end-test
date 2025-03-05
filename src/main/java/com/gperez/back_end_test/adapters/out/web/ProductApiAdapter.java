package com.gperez.back_end_test.adapters.out.web;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.gperez.back_end_test.domain.exceptions.ProductApiException;
import com.gperez.back_end_test.domain.exceptions.ProductNotFoundException;
import com.gperez.back_end_test.domain.model.ProductDetails;
import com.gperez.back_end_test.ports.out.web.ProductApiPort;

import reactor.core.publisher.Mono;

@Component
public class ProductApiAdapter implements ProductApiPort {

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<List<String>> fetchSimilarProductIds(String productId) {
        return webClient
            .get()
            .uri("/product/{productId}/similarids", productId)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
            .timeout(Duration.ofSeconds(5))
            .onErrorResume(TimeoutException.class, e -> Mono.empty())
            .onErrorResume(WebClientResponseException.class, e -> handleFetchSimilarProductIdsError(e));
    }   

    @Override
    public Mono<ProductDetails> fetchProductDetails(String productId) {
        return webClient
            .get()
            .uri("/product/{productId}", productId) 
            .retrieve()
            .bodyToMono(ProductDetails.class)  
            .timeout(Duration.ofSeconds(5))
            .onErrorResume(TimeoutException.class, e -> Mono.empty())
            .onErrorResume(WebClientResponseException.class, e -> handleFetchProductDetailsError(e));
    }

    public <T> Mono<T> handleFetchSimilarProductIdsError(WebClientResponseException e) {
        if(e.getStatusCode().value() == 404) {
            return Mono.error(new ProductNotFoundException());
        }
        return Mono.error(new ProductApiException(e.getMessage(), e.getStatusCode().value()));
    }

    public <T> Mono<T> handleFetchProductDetailsError(WebClientResponseException e) {
        return Mono.empty();
    }
    
}
