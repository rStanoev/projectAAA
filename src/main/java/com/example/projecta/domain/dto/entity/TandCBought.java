package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tandc_bought")
public class TandCBought {

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
    private TandC tandC;


    public TandCBought() {
    }

    public TandCBought(String name, Double price, TandC tandC) {
        this.name = name;
        this.price = price;
        this.tandC = tandC;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
