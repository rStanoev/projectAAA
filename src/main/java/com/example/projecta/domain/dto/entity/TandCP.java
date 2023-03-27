package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "table_chairP")
public class TandCP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private TandC tandC;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CommentsTC> commentsTCSet;


    public TandCP() {
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

    public Set<CommentsTC> getCommentsTCSet() {
        return commentsTCSet;
    }

    public void setCommentsTCSet(Set<CommentsTC> commentsTCSet) {
        this.commentsTCSet = commentsTCSet;
    }
}
