package com.example.projecta.domain.dto.entity;


import com.example.projecta.domain.dto.model.ScListModel;
import com.example.projecta.domain.dto.model.ShoppingCartModel;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @DateTimeFormat()
    private LocalDate born;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PcP> pcs;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<HardwareP> hardware;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PeripheralP> peripherals;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TandCP> tANDcs;

    @ManyToOne
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PcBought> pcsBought;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<HardwareBought> hardwareBought;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PeripheralBought> peripheralsBought;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TandCBought> tANDcsBought;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void add(Role role) {
        roles.add(role);
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<PcP> getPcs() {
        return pcs;
    }

    public void setPcs(Set<PcP> pcs) {
        this.pcs = pcs;
    }

    public Set<HardwareP> getHardware() {
        return hardware;
    }

    public void setHardware(Set<HardwareP> hardware) {
        this.hardware = hardware;
    }

    public Set<PeripheralP> getPeripherals() {
        return peripherals;
    }

    public void setPeripherals(Set<PeripheralP> peripherals) {
        this.peripherals = peripherals;
    }

    public Set<TandCP> gettANDcs() {
        return tANDcs;
    }

    public void settANDcs(Set<TandCP> tANDcs) {
        this.tANDcs = tANDcs;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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


    public void removeHardware(HardwareP hardwareP) {

        hardware.remove(hardwareP);
    }

    public Set<PcBought> getPcsBought() {
        return pcsBought;
    }

    public void setPcsBought(Set<PcBought> pcsBought) {
        this.pcsBought = pcsBought;
    }

    public Set<HardwareBought> getHardwareBought() {
        return hardwareBought;
    }

    public void setHardwareBought(Set<HardwareBought> hardwareBought) {
        this.hardwareBought = hardwareBought;
    }

    public Set<PeripheralBought> getPeripheralsBought() {
        return peripheralsBought;
    }

    public void setPeripheralsBought(Set<PeripheralBought> peripheralsBought) {
        this.peripheralsBought = peripheralsBought;
    }

    public Set<TandCBought> gettANDcsBought() {
        return tANDcsBought;
    }

    public void settANDcsBought(Set<TandCBought> tANDcsBought) {
        this.tANDcsBought = tANDcsBought;
    }
}
