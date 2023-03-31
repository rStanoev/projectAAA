package com.example.projecta.domain.dto.binding;

import com.example.projecta.domain.dto.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class MessagesBindingModel {

    @NotNull
    private String text;

    public MessagesBindingModel() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
