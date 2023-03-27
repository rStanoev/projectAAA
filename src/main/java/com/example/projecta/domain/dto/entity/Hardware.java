package com.example.projecta.domain.dto.entity;

import com.example.projecta.domain.dto.entity.enums.HardwareEnum;

import javax.persistence.*;

@Entity
@Table(name = "hardware")
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HardwareEnum type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public Hardware() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HardwareEnum getType() {
        return type;
    }

    public void setType(HardwareEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
