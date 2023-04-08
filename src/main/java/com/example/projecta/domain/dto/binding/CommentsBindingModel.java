package com.example.projecta.domain.dto.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentsBindingModel {

    @NotNull
    @Size(min = 1)
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
