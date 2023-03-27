package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.TandC;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class TCModel {
    private Long id;


    private String name;


    private Double price;


    private TandC tandC;

    public TCModel() {
    }

    public TCModel(Long id, String name, Double price, TandC tandC) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tandC = tandC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TandC getTandC() {
        return tandC;
    }

    public void setTandC(TandC tandC) {
        this.tandC = tandC;
    }
}