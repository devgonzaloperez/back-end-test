package com.gperez.back_end_test.mocks;

import java.util.List;

import com.gperez.back_end_test.domain.model.ProductDetails;

public final class ProductMocks {
    
    public static final ProductDetails PRODUCT_2_MOCK = new ProductDetails("2", "Mock Product 2", 100.00, true);
    public static final ProductDetails PRODUCT_3_MOCK = new ProductDetails("3", "Mock Product 3", 100.00, true);

    public static final List<ProductDetails> PRODUCT_LIST_MOCK = List.of(PRODUCT_2_MOCK, PRODUCT_3_MOCK);

}
