package com.example.projecta.domain.dto.model;

public class ProductNotFoundException extends RuntimeException{

    private final long produtcId;




    public ProductNotFoundException(long produtcId) {

        super("Product with ID " + produtcId + " not found!");


        this.produtcId = produtcId;

    }

    public long getProdutcId() {
        return produtcId;
    }


}
