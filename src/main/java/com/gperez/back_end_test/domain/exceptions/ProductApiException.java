package com.gperez.back_end_test.domain.exceptions;

public class ProductApiException extends RuntimeException {

    private int status;

    public ProductApiException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
