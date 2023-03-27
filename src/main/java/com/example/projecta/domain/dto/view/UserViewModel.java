package com.example.projecta.domain.dto.view;

import com.example.projecta.domain.dto.entity.Gender;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;

import java.time.LocalDate;

public class UserViewModel {

    private Long id;
    private Gender genderEnum;

    private String username;

    private String fullName;

    private String email;

    private LocalDate born;

    public UserViewModel() {
    }

    public UserViewModel(Long id, Gender genderEnum, String username, String fullName, String email, LocalDate born) {
        this.id = id;
        this.genderEnum = genderEnum;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.born = born;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(Gender genderEnum) {
        this.genderEnum = genderEnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }
}
