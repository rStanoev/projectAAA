package com.example.projecta.domain.dto.entity;

import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;

import javax.persistence.*;

@Entity
@Table(name = "pc_laptop")
public class Pc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PcAndLaptopEnum type;

    @Column(columnDefinition = "TEXT")
    private String description;


    public Pc() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
