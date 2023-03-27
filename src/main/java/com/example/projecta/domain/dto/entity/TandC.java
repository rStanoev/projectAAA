package com.example.projecta.domain.dto.entity;

import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;

import javax.persistence.*;

@Entity
@Table(name = "table_chair")
public class TandC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChairAndTableEnum type;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    public TandC() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ChairAndTableEnum getType() {
        return type;
    }

    public void setType(ChairAndTableEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
