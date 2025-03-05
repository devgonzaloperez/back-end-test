package com.gperez.back_end_test.ports.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gperez.back_end_test.application.dtos.ResponseDto;
import com.gperez.back_end_test.domain.model.ProductDetails;

import reactor.core.publisher.Mono;

public interface SimilarProductsControllerPort {
    
    Mono<ResponseEntity<ResponseDto<List<ProductDetails>>>> getSimilarProducts(String productId);

}
