package com.gperez.back_end_test.adapters.in.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gperez.back_end_test.application.dtos.ErrorResponseDto;
import com.gperez.back_end_test.application.dtos.ResponseDto;
import com.gperez.back_end_test.domain.model.ProductDetails;
import com.gperez.back_end_test.ports.in.usecases.SimilarProductsUseCase;
import com.gperez.back_end_test.ports.in.web.SimilarProductsControllerPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Similar Products Controller", description = "Controlador de productos ")
public class SimilarProductsControllerAdapter implements SimilarProductsControllerPort {

    @Autowired
    private final SimilarProductsUseCase similarProductsUseCase;

    @Override
    @GetMapping("/{productId}/similar")
    @Operation(
        summary = "Obtener productos similares", 
        description = "Obtiene una lista de productos similares basado en el ID del producto proporcionado"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "OK", 
            content = @Content(mediaType = "application/json"),
            useReturnTypeSchema = true
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Product Not Found", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal Server Error", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
        )
    })
    public Mono<ResponseEntity<ResponseDto<List<ProductDetails>>>> getSimilarProducts(
        @Parameter(description = "ID del producto para buscar similares", required = true)
        @PathVariable 
        String productId
    ) {
        
        Mono<List<ProductDetails>> similarProductsMono = similarProductsUseCase.getSimilarProducts(productId);

        Mono<ResponseDto<List<ProductDetails>>> responseDtoMono = similarProductsMono.map((similarProducts) -> {
            ResponseDto<List<ProductDetails>> responseDto = new ResponseDto<>();
            responseDto.setContent(similarProducts);
            responseDto.setDescription(HttpStatus.OK.getReasonPhrase());

            return responseDto;
        });

        return responseDtoMono.map((responseDto) -> ResponseEntity.ok(responseDto));
        
    }
    
}
