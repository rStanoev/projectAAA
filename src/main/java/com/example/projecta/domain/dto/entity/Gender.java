package com.example.projecta.domain.dto.entity;

import com.example.projecta.domain.dto.entity.enums.GenderEnum;

import javax.persistence.*;

@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum type;

    public Gender() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenderEnum getType() {
        return type;
    }

    public void setType(GenderEnum type) {
        this.type = type;
    }
}
