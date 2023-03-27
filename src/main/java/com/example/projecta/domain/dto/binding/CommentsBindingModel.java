package com.example.projecta.domain.dto.binding;

public class CommentsBindingModel {

    private String comment;

    private Long id;

    public CommentsBindingModel() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentsBindingModel(String comment, Long id) {
        this.comment = comment;
        this.id = id;
    }
}
