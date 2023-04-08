package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.Pc;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class PcModel {

    private Long id;


    private String name;


    private Double price;

    private String description;


    private Pc pc;

    public PcModel() {
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

    public Pc getPc() {
        return pc;
    }

    public void setPc(Pc pc) {
        this.pc = pc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
