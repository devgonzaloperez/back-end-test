package com.gperez.back_end_test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetails {
    
    private String id;
    private String name;
    private Double price;
    private Boolean availability;

}
