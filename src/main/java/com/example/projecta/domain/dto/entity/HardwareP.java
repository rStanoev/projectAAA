package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hardwareP")
public class HardwareP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private String description;

    @ManyToOne
    private Hardware hardware;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CommentsHC> commentsHCSet;

    public HardwareP() {
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

    public Set<CommentsHC> getCommentsHCSet() {
        return commentsHCSet;
    }

    public void setCommentsHCSet(Set<CommentsHC> commentsHCSet) {
        this.commentsHCSet = commentsHCSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
