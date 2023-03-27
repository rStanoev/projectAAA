package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "commentshc")
public class CommentsHC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "Text")
    private String comment;

    @ManyToOne
    private User users;

    private Long hcId;


    public CommentsHC() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Long getHcId() {
        return hcId;
    }

    public void setHcId(Long hcId) {
        this.hcId = hcId;
    }
}
