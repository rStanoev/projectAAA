package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hardware_bought")
public class HardwareBought {

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
    private Hardware hardware;

    public HardwareBought() {
    }

    public HardwareBought(String name, Double price, Hardware hardware) {
        this.name = name;
        this.price = price;
        this.hardware = hardware;
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

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
