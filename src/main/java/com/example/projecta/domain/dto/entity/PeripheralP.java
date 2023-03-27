package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "peripheralsP")
public class PeripheralP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Peripheral peripheral;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CommentsPE> commentsPESet;

    public PeripheralP() {
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

    public Set<CommentsPE> getCommentsPESet() {
        return commentsPESet;
    }

    public void setCommentsPESet(Set<CommentsPE> commentsPESet) {
        this.commentsPESet = commentsPESet;
    }
}
