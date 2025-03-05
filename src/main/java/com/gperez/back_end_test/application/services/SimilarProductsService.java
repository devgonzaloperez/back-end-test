package com.gperez.back_end_test.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gperez.back_end_test.domain.model.ProductDetails;
import com.gperez.back_end_test.ports.in.usecases.SimilarProductsUseCase;
import com.gperez.back_end_test.ports.out.web.ProductApiPort;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class SimilarProductsService implements SimilarProductsUseCase {    

    @Autowired
    private ProductApiPort productApiPort;

    @Override
    @Cacheable(value = "similar-products-cache", key = "#productId")
    public Mono<List<ProductDetails>> getSimilarProducts(String productId) {

        return fetchSimilarProductIds(productId)
            .flatMapMany((similarProductIds) -> Flux
                .fromIterable(similarProductIds)
                .flatMap((id) -> fetchProductDetails(id))
            )
            .collectList();
    }

    private Mono<List<String>> fetchSimilarProductIds(String productId) {
        return productApiPort.fetchSimilarProductIds(productId);
    }

    public Mono<ProductDetails> fetchProductDetails(String productId) {
        return productApiPort.fetchProductDetails(productId);
    }
    
}
