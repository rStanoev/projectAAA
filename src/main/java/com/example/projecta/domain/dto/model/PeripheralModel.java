package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.Peripheral;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class PeripheralModel {
    private Long id;


    private String name;


    private Double price;

    private String description;


    private Peripheral peripheral;

    public PeripheralModel() {
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

    public Peripheral getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Peripheral peripheral) {
        this.peripheral = peripheral;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
