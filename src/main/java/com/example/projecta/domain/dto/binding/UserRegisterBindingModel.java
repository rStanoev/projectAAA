package com.example.projecta.domain.dto.binding;


import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserRegisterBindingModel {

    @Size(min = 5, max = 20)
    @NotNull
    private String fullName;

    @Size(min = 3, max = 20)
    @NotNull
    @NotBlank
    private String username;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @Size(min = 3)
    @NotNull
    @NotBlank
    private String password;

    @Size(min = 3)
    @NotNull
    @NotBlank
    private String confirmPassword;

    @NotNull
    private GenderEnum gender;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate born;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
