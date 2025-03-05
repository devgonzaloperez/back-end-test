package com.gperez.back_end_test.ports.out.web;

import java.util.List;

import com.gperez.back_end_test.domain.model.ProductDetails;

import reactor.core.publisher.Mono;


public interface ProductApiPort {

    Mono<List<String>> fetchSimilarProductIds(String productId);

    Mono<ProductDetails> fetchProductDetails(String productIds);

}

