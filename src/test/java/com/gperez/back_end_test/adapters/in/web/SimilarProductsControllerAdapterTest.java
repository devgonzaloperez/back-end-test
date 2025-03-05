package com.gperez.back_end_test.adapters.in.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.gperez.back_end_test.mocks.ProductMocks;
import com.gperez.back_end_test.ports.in.usecases.SimilarProductsUseCase;

import reactor.core.publisher.Mono;

@WebMvcTest(SimilarProductsControllerAdapter.class)
public class SimilarProductsControllerAdapterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean 
    private SimilarProductsUseCase similarProductsUseCase;

    @Test
    void testGetSimilarProducts() throws Exception {

        Mockito
            .when(similarProductsUseCase.getSimilarProducts("1"))
            .thenReturn(Mono.just(ProductMocks.PRODUCT_LIST_MOCK));

        webTestClient
            .get()
            .uri("/product/1/similar")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.description").isEqualTo("OK")
            .jsonPath("$.content").isArray()
            .jsonPath("$.content.length()").isEqualTo(2)
            .jsonPath("$.content[0].id").isEqualTo("2")
            .jsonPath("$.content[1].id").isEqualTo("3");

        Mockito.verify(similarProductsUseCase, Mockito.times(1)).getSimilarProducts("1");

    }

}
    

