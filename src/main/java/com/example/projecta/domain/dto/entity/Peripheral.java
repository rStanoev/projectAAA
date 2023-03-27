package com.example.projecta.domain.dto.entity;

import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;

import javax.persistence.*;

@Entity
@Table(name = "peripherals")
public class Peripheral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PeripheralEnum type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    public Peripheral() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeripheralEnum getType() {
        return type;
    }

    public void setType(PeripheralEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
