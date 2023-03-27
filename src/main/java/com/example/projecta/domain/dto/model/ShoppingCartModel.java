package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.Pc;
import com.example.projecta.domain.dto.entity.Peripheral;
import com.example.projecta.domain.dto.entity.TandC;

public class ShoppingCartModel {

    private Long id;


    private String name;


    private Double price;


    private TandC tandC;

    private Peripheral peripheral;

    private Hardware hardware;

    private Pc pc;

    public ShoppingCartModel() {
    }

    public ShoppingCartModel(Long id, String name, Double price, TandC tandC) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tandC = tandC;
    }

    public ShoppingCartModel(Long id, String name, Double price, Peripheral peripheral) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.peripheral = peripheral;
    }

    public ShoppingCartModel(Long id, String name, Double price, Hardware hardware) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.hardware = hardware;
    }

    public ShoppingCartModel(Long id, String name, Double price, Pc pc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pc = pc;
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

    public Peripheral getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Peripheral peripheral) {
        this.peripheral = peripheral;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public Pc getPc() {
        return pc;
    }

    public void setPc(Pc pc) {
        this.pc = pc;
    }
}
