package com.example.projecta.domain.dto.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "commentspe")
public class CommentsPE {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(nullable = false, columnDefinition = "Text")
        private String comment;

        @ManyToOne
        private User user;

        private Long peId;

        public CommentsPE() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPeId() {
            return peId;
        }

        public void setPeId(Long peId) {
            this.peId = peId;
        }
}
