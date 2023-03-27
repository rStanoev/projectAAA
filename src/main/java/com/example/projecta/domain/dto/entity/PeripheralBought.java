package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "peripheral_bought")
public class PeripheralBought {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime time;

    @ManyToOne
    private Peripheral peripheral;

    public PeripheralBought() {
    }

    public PeripheralBought(String name, Double price, Peripheral peripheral) {
        this.name = name;
        this.price = price;
        this.peripheral = peripheral;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
