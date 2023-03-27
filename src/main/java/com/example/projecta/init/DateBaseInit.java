package com.example.projecta.init;


import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.*;
import com.example.projecta.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DateBaseInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final PcRepository pcRepository;

    private final PeripheralRepository peripheralRepository;

    private final HardwareRepository hardwareRepository;

    private final TandCRepository tandCRepository;

    private final GenderRepository genderRepository;

    @Autowired
    public DateBaseInit(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, PcRepository pcRepository, PeripheralRepository peripheralRepository, HardwareRepository hardwareRepository, TandCRepository tandCRepository, GenderRepository genderRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.pcRepository = pcRepository;
        this.peripheralRepository = peripheralRepository;
        this.hardwareRepository = hardwareRepository;
        this.tandCRepository = tandCRepository;

        this.genderRepository = genderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() != 0) {
            return;
        }
        Arrays.stream(UserRoles.values())
                .forEach(userRole -> {
                    Role role = new Role();
                    role.setName(userRole);

                    this.roleRepository.save(role);
                });

        Arrays.stream(PcAndLaptopEnum.values())
                .forEach(pcAndLaptop -> {
                    Pc pc = new Pc();
                    pc.setType(pcAndLaptop);
                    pc.setDescription("PC stands for personal computer. It is a broad term used to describe any computing device meant for everyday, individual use. Laptops, desktops, tablets, smartphones, and other devices can all considered PCs");

                    this.pcRepository.save(pc);
                });

        Arrays.stream(PeripheralEnum.values())
                .forEach(peripheral -> {
                    Peripheral peripheral1 = new Peripheral();
                    peripheral1.setType(peripheral);
                    peripheral1.setDescription("A peripheral device or peripheral is an auxiliary hardware device used to transfer information into and out of a computer. ");

                    this.peripheralRepository.save(peripheral1);
                });

        Arrays.stream(HardwareEnum.values())
                .forEach(hardwareEnum -> {
                   Hardware hardware = new Hardware();
                    hardware.setType(hardwareEnum);
                    hardware.setDescription("Hardware refers to the external and internal devices and equipment that enable you to perform major functions such as input, output, storage, communication, processing, and more.");

                    this.hardwareRepository.save(hardware);
                });

        Arrays.stream(ChairAndTableEnum.values())
                .forEach(ct -> {
                    TandC tandC = new TandC();
                    tandC.setType(ct);
                    tandC.setDescription(" a table that is designed especially for gambling and that often has depressions for counters and designs painted,A gaming chair is a type of chair designed for the comfort of gamers.");

                    this.tandCRepository.save(tandC);
                });

        Arrays.stream(GenderEnum.values())
                .forEach(gm -> {
                    Gender gender = new Gender();
                    gender.setType(gm);

                    this.genderRepository.save(gender);
                });
        Set<Role> roles = new HashSet<>();
        User user = new User();
        user.setUsername("gosho");
        user.setEmail("gogo@abv.bg");
        user.setPassword(passwordEncoder.encode("11111"));
        roles.add(this.roleRepository.findByName(UserRoles.OWNER));
        user.setRoles(roles);
        user.setGender(this.genderRepository.findByType(GenderEnum.MALE));
        user.setFullName("Georgi Savov");
        user.setBorn(LocalDate.of(2001, 11,  26));

        this.userRepository.saveAndFlush(user);
    }
}
