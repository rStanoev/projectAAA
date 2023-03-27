package com.example.projecta.domain.dto.binding;

import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;

import javax.validation.constraints.*;

public class PcBindingModel {

    @NotNull
    @Size(min = 2, max = 35)
    private String name;

    @NotNull
    @Min(0)
    @Max(100000)
    private Double price;

    @NotNull
    private PcAndLaptopEnum type;

    @NotNull
    @Size(min = 5)
    private String description;

    public PcBindingModel() {
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

    public PcAndLaptopEnum getType() {
        return type;
    }

    public void setType(PcAndLaptopEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
