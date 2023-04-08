package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pc_laptopP")
public class PcP {

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
    private Pc pc;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CommentsPC> commentsPCSet;

    public PcP() {
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

    public Pc getPc() {
        return pc;
    }

    public void setPc(Pc pc) {
        this.pc = pc;
    }

    public Set<CommentsPC> getCommentsPCSet() {
        return commentsPCSet;
    }

    public void setCommentsPCSet(Set<CommentsPC> commentsPCSet) {
        this.commentsPCSet = commentsPCSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
