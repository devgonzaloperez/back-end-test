package com.gperez.back_end_test.ports.in.usecases;

import java.util.List;

import com.gperez.back_end_test.domain.model.ProductDetails;

import reactor.core.publisher.Mono;

public interface SimilarProductsUseCase {
    
    Mono<List<ProductDetails>> getSimilarProducts(String productId);

}
