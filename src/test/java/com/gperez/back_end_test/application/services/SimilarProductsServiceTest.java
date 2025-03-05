package com.gperez.back_end_test.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gperez.back_end_test.domain.model.ProductDetails;
import com.gperez.back_end_test.mocks.ProductMocks;
import com.gperez.back_end_test.ports.out.web.ProductApiPort;

import reactor.core.publisher.Mono;

class SimilarProductsServiceTest {

    @Mock
    private ProductApiPort productApiPort;

    private SimilarProductsService similarProductsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        similarProductsService = new SimilarProductsService(productApiPort);
    }

    @Test
    void testGetSimilarProducts() {
        String productId = "1";
        List<String> similarProductIdsMock = List.of("2", "3");

        Mockito.when(productApiPort.fetchSimilarProductIds(productId)).thenReturn(Mono.just(similarProductIdsMock));
        Mockito.when(productApiPort.fetchProductDetails("2")).thenReturn(Mono.just(ProductMocks.PRODUCT_2_MOCK));
        Mockito.when(productApiPort.fetchProductDetails("3")).thenReturn(Mono.just(ProductMocks.PRODUCT_3_MOCK));

        List<ProductDetails> result = similarProductsService.getSimilarProducts(productId).block();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("2", result.get(0).getId());
        assertEquals("3", result.get(1).getId());

        Mockito.verify(productApiPort, Mockito.times(1)).fetchSimilarProductIds(productId);
        Mockito.verify(productApiPort, Mockito.times(1)).fetchProductDetails("2");
        Mockito.verify(productApiPort, Mockito.times(1)).fetchProductDetails("3");
    }

}