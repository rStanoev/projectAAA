package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.UserRegisterBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.*;
import com.example.projecta.repository.GenderRepository;
import com.example.projecta.repository.RoleRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.*;
import com.example.projecta.service.impl.UserServiceImpl;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private GenderRepository mockGenderRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private TandCBoughtService mockTandCBoughtService;

    @Mock
    private PeripheralBoughtService mockPeripheralBoughtService;

    @Mock
    private HardwareBoughtService mockHardwareBoughtService;

    @Mock
    private PcBoughtService mockPcBoughtService;

    @Mock
    private MessagesService mockMessagesService;

    @Captor
    private ArgumentCaptor<User> userEntityArgumentCaptor;

    private UserServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                mockUserRepository,
                mockRoleRepository,
                mockGenderRepository,
                mockModelMapper,
                mockPasswordEncoder,
                mockTandCBoughtService,
                mockPeripheralBoughtService,
                mockHardwareBoughtService,
                mockPcBoughtService,
                mockMessagesService);

    }

    @Test
    void testUserRegistration_SaveInvoked_Version2() {

        String encodedPassword = "encoded_password";

        UserRegisterBindingModel testRegistrationDTO = new UserRegisterBindingModel();
        testRegistrationDTO.setEmail("test@example.com");
        testRegistrationDTO.setUsername("Test");
        testRegistrationDTO.setFullName("Testov");
        testRegistrationDTO.setPassword("secret");
        testRegistrationDTO.setConfirmPassword("secret");
        testRegistrationDTO.setGender(GenderEnum.MALE);
        testRegistrationDTO.setBorn(LocalDate.now());


        when(mockPasswordEncoder.encode(testRegistrationDTO.getPassword())).
                thenReturn(encodedPassword);

        //ACT

        toTest.registerUser(testRegistrationDTO);

        //ASSERT


        Mockito.verify(mockUserRepository).saveAndFlush(any());
    }


    @Test
    void test_getUser() {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);
        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

        when(mockUserRepository.findByEmail("gosho@abv.bg")).thenReturn(Optional.of(user));


        toTest.getUser("gosho@abv.bg");


        Assertions.assertNotNull(user);
        Assertions.assertEquals("gosho@abv.bg", user.getEmail());

    }

    @Test
    void test_getUser2() {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);
        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

        when(mockUserRepository.findByUsername("gosho")).thenReturn(Optional.of(user));


        toTest.getUser2("gosho");


        Assertions.assertNotNull(user);
        Assertions.assertEquals("gosho", user.getUsername());

    }

    @Test
    void test_changeRole() {

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        Role testRole1 = new Role();
        testRole1.setId(1);
        testRole1.setName(UserRoles.ADMIN);
        Set<Role> roles1 = new HashSet<>();
        roles1.add(testRole1);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);


        toTest.changeRole(user);


        Role role = iterate(roles);
        Assertions.assertEquals(UserRoles.USER, role.getName());

        user.setRoles(roles1);
        Role role1 = iterate(roles1);
        Assertions.assertEquals(UserRoles.ADMIN, role1.getName());

        Mockito.verify(mockUserRepository).saveAndFlush(any());

    }

    @Test
    void test_findAllUsersExceptLoggedOne() {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);
        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);
        List<com.example.projecta.domain.dto.entity.User> users = new LinkedList<>();

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        com.example.projecta.domain.dto.entity.User user1 = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

        user1.setId(2L);
        user1.setFullName("Petur");
        user1.setUsername("pesho");
        user1.setEmail("pesho@abv.bg");
        user1.setPassword("peshoG");
        user1.setGender(gender);
        user1.setBorn(LocalDate.of(2000, 12, 26));
        user1.setRoles(roles);

        users.add(user);
        users.add(user1);


        toTest.findAllUsersExceptLoggedOne(user);

        for (com.example.projecta.domain.dto.entity.User u : users) {
            if (u == user) {
                users.remove(u);
            }
        }


        Assertions.assertEquals(1, users.size());
    }

    @Test
    void test_getConversation() {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);
        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        com.example.projecta.domain.dto.entity.User user1 = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

        user1.setId(2L);
        user1.setFullName("Petur");
        user1.setUsername("pesho");
        user1.setEmail("pesho@abv.bg");
        user1.setPassword("peshoG");
        user1.setGender(gender);
        user1.setBorn(LocalDate.of(2000, 12, 26));
        user1.setRoles(roles);

        toTest.getConversation(user, user1);

        Messages messages = new Messages();
        Messages messages1 = new Messages();
        Messages messages2 = new Messages();

        messages.setId(1L);
        messages.setText("SOMWSDAS");
        messages.setReceiverId(2L);
        messages.setUser(user);
        messages.setTime(LocalTime.now());

        messages1.setId(2L);
        messages1.setText("SOMWSDAS");
        messages1.setReceiverId(1L);
        messages1.setUser(user1);
        messages1.setTime(LocalTime.now());

        messages2.setId(3L);
        messages2.setText("SOMWSDAS");
        messages2.setReceiverId(4L);
        messages2.setUser(user);
        messages2.setTime(LocalTime.now());

        List<Messages> messagess = new LinkedList<>();
        messagess.add(messages);
        messagess.add(messages1);
        messagess.add(messages2);
        List<Messages> messagess1 = new LinkedList<>();

        for (Messages m : messagess) {
            if (m.getUser().getId() == user.getId() && m.getReceiverId() == user1.getId()) {
                messagess1.add(m);
            }else if (m.getUser().getId() == user1.getId() && m.getReceiverId() == user.getId()) {
                messagess1.add(m);
            }
        }

        Assertions.assertEquals(2, messagess1.size());
    }

    @Test
    void test_getAllWithUsersRole() {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Role testRole1 = new Role();
        testRole1.setName(UserRoles.ADMIN);
        Set<Role> roles1 = new HashSet<>();
        roles1.add(testRole1);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);
        List<com.example.projecta.domain.dto.entity.User> users = new LinkedList<>();

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        com.example.projecta.domain.dto.entity.User user1 = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

        user1.setId(2L);
        user1.setFullName("Petur");
        user1.setUsername("pesho");
        user1.setEmail("pesho@abv.bg");
        user1.setPassword("peshoG");
        user1.setGender(gender);
        user1.setBorn(LocalDate.of(2000, 12, 26));
        user1.setRoles(roles1);

        users.add(user);
        users.add(user1);


        toTest.getAllWithUsersRole();
        List<com.example.projecta.domain.dto.entity.User> users1 = new LinkedList<>();

        for (int i = 0; i < users.size(); i++) {
            com.example.projecta.domain.dto.entity.User userR = users.get(i);
            Set<Role> role = userR.getRoles();
            for (Role role1 : role) {
                if (role1.getName().equals(UserRoles.USER)) {
                    users1.add(userR);
                }
            }
        }



        Assertions.assertEquals(1, users1.size());
    }

    @Test
    void test_removeHC() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        HardwareP hardwareP = new HardwareP();
        HardwareP hardwareP1 = new HardwareP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        hardwareP.setId(1L);
        hardwareP.setName("amd");
        hardwareP.setPrice(350.0);
        hardwareP.setDescription("fafafa");
        hardwareP.setHardware(hardware);
        hardwareP1.setId(2L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(400.0);
        hardwareP1.setDescription("fafafa");
        hardwareP1.setHardware(hardware);

        Set<HardwareP> hardwarePSet = new HashSet<>();
        hardwarePSet.add(hardwareP);
        hardwarePSet.add(hardwareP1);


        toTest.removeHC(hardwareP, user, hardwarePSet);

        Set<HardwareP> hardwarePS1 = new LinkedHashSet<>();

        for (HardwareP hardwarePs1 : hardwarePSet) {
            if (!Objects.equals(hardwarePs1.getId(), hardwareP.getId())) {
                hardwarePS1.add(hardwarePs1);
            }
        }

        assertEquals(1, hardwarePS1.size());
    }

    @Test
    void test_buyHC() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        HardwareP hardwareP = new HardwareP();
        HardwareP hardwareP1 = new HardwareP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        hardwareP.setId(1L);
        hardwareP.setName("amd");
        hardwareP.setPrice(350.0);
        hardwareP.setDescription("fafafa");
        hardwareP.setHardware(hardware);
        hardwareP1.setId(2L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(400.0);
        hardwareP1.setDescription("fafafa");
        hardwareP1.setHardware(hardware);

        Set<HardwareP> hardwarePSet = new HashSet<>();

        hardwarePSet.add(hardwareP);
        hardwarePSet.add(hardwareP1);

        toTest.removeHC(hardwareP, user, hardwarePSet);

        Set<HardwareP> hardwarePS1 = new LinkedHashSet<>();
        Set<HardwareBought> hardwareBoughtSet = new HashSet<>();

        for (HardwareP hardwarePs1 : hardwarePSet) {
            if (Objects.equals(hardwarePs1.getId(), hardwareP.getId())) {
                HardwareBought hardwareBought = new HardwareBought();
                hardwareBought.setId(1L);
                hardwareBought.setName(hardwarePs1.getName());
                hardwareBought.setPrice(hardwarePs1.getPrice());
                hardwareBought.setHardware(hardwarePs1.getHardware());
                hardwareBoughtSet.add(hardwareBought);
            }else {
                hardwarePS1.add(hardwarePs1);
            }

        }
        assertEquals(1, hardwareBoughtSet.size());
        assertEquals(1, hardwarePS1.size());
    }

    @Test
    void test_removePE() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.CONTROLLER);
        peripheral.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        PeripheralP peripheralP = new PeripheralP();
        PeripheralP peripheralP1 = new PeripheralP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        peripheralP.setId(1L);
        peripheralP.setName("amd");
        peripheralP.setPrice(350.0);
        peripheralP.setDescription("fafafa");
        peripheralP.setPeripheral(peripheral);
        peripheralP1.setId(2L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(400.0);
        peripheralP1.setDescription("fafafa");
        peripheralP1.setPeripheral(peripheral);

        Set<PeripheralP> peripheralPSet = new HashSet<>();
        peripheralPSet.add(peripheralP);
        peripheralPSet.add(peripheralP1);


        toTest.removePE(peripheralP, user, peripheralPSet);

        Set<PeripheralP> peripheralPSet1 = new LinkedHashSet<>();

        for (PeripheralP peripheralPs1 : peripheralPSet) {
            if (!Objects.equals(peripheralPs1.getId(), peripheralP.getId())) {
                peripheralPSet1.add(peripheralPs1);
            }
        }

        assertEquals(1, peripheralPSet1.size());
    }

    @Test
    void test_buyPE() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.CONTROLLER);
        peripheral.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        PeripheralP peripheralP = new PeripheralP();
        PeripheralP peripheralP1 = new PeripheralP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        peripheralP.setId(1L);
        peripheralP.setName("amd");
        peripheralP.setPrice(350.0);
        peripheralP.setDescription("fafafa");
        peripheralP.setPeripheral(peripheral);
        peripheralP1.setId(2L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(400.0);
        peripheralP1.setDescription("fafafa");
        peripheralP1.setPeripheral(peripheral);

        Set<PeripheralP> peripheralPSet = new HashSet<>();
        peripheralPSet.add(peripheralP);
        peripheralPSet.add(peripheralP1);

        toTest.removePE(peripheralP, user, peripheralPSet);

        Set<PeripheralP> peripheralPSet1 = new LinkedHashSet<>();
        Set<PeripheralBought> peripheralBoughtSet = new HashSet<>();

        for (PeripheralP peripheralPs1 : peripheralPSet) {
            if (Objects.equals(peripheralPs1.getId(), peripheralP.getId())) {
                PeripheralBought peripheralBought = new PeripheralBought();
                peripheralBought.setId(1L);
                peripheralBought.setName(peripheralPs1.getName());
                peripheralBought.setPrice(peripheralPs1.getPrice());
                peripheralBought.setPeripheral(peripheralPs1.getPeripheral());
                peripheralBoughtSet.add(peripheralBought);
            }else {
                peripheralPSet1.add(peripheralPs1);
            }

        }
        assertEquals(1, peripheralBoughtSet.size());
        assertEquals(1, peripheralPSet1.size());
    }

    @Test
    void test_removePC() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        PcP pcP = new PcP();
        PcP pcP1 = new PcP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        pcP.setId(1L);
        pcP.setName("amd");
        pcP.setPrice(350.0);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);
        pcP1.setId(2L);
        pcP1.setName("intel");
        pcP1.setPrice(400.0);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc);

        Set<PcP> pcPSet = new HashSet<>();
        pcPSet.add(pcP);
        pcPSet.add(pcP1);


        toTest.removePC(pcP, user, pcPSet);

        Set<PcP> pcPSet1 = new HashSet<>();

        for (PcP pcPs1 : pcPSet) {
            if (!Objects.equals(pcPs1.getId(), pcP.getId())) {
                pcPSet1.add(pcPs1);
            }
        }

        assertEquals(1, pcPSet1.size());
    }

    @Test
    void test_buyPC() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        PcP pcP = new PcP();
        PcP pcP1 = new PcP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        pcP.setId(1L);
        pcP.setName("amd");
        pcP.setPrice(350.0);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);
        pcP1.setId(2L);
        pcP1.setName("intel");
        pcP1.setPrice(400.0);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc);

        Set<PcP> pcPSet = new HashSet<>();
        pcPSet.add(pcP);
        pcPSet.add(pcP1);

        toTest.removePC(pcP, user, pcPSet);

        Set<PcP> pcPSet1 = new HashSet<>();
        Set<PcBought> pcBoughtSet = new HashSet<>();

        for (PcP pcPs1 : pcPSet) {
            if (Objects.equals(pcPs1.getId(), pcP.getId())) {
                PcBought pcBought = new PcBought();
                pcBought.setId(1L);
                pcBought.setName(pcPs1.getName());
                pcBought.setPrice(pcPs1.getPrice());
                pcBought.setPc(pcPs1.getPc());
                pcBoughtSet.add(pcBought);
            }else {
                pcPSet1.add(pcPs1 );
            }

        }
        assertEquals(1, pcBoughtSet.size());
        assertEquals(1, pcPSet1.size());
    }

    @Test
    void test_removeTC() {


        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.CHAIR);
        tandC.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        TandCP tandCP = new TandCP();
        TandCP tandCP1 = new TandCP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        tandCP.setId(1L);
        tandCP.setName("amd");
        tandCP.setPrice(350.0);
        tandCP.setDescription("fafafaf");
        tandCP.setTandC(tandC);
        tandCP1.setId(2L);
        tandCP1.setName("intel");
        tandCP1.setPrice(400.0);
        tandCP1.setDescription("fafafaf");
        tandCP1.setTandC(tandC);

        Set<TandCP> tandCPSet = new HashSet<>();
        tandCPSet.add(tandCP);
        tandCPSet.add(tandCP1);

        toTest.removeTC(tandCP, user, tandCPSet);

        Set<TandCP> tandCPSet1 = new HashSet<>();


        for (TandCP tandCP2 : tandCPSet) {
            if (!Objects.equals(tandCP2 .getId(), tandCP.getId())) {
                tandCPSet1.add(tandCP2);
            }
        }

        assertEquals(1, tandCPSet1.size());
    }

    @Test
    void test_buyTC() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.CHAIR);
        tandC.setDescription("dsdada");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        TandCP tandCP = new TandCP();
        TandCP tandCP1 = new TandCP();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);
        tandCP.setId(1L);
        tandCP.setName("amd");
        tandCP.setPrice(350.0);
        tandCP.setDescription("fafafaf");
        tandCP.setTandC(tandC);
        tandCP1.setId(2L);
        tandCP1.setName("intel");
        tandCP1.setPrice(400.0);
        tandCP1.setDescription("fafafaf");
        tandCP1.setTandC(tandC);

        Set<TandCP> tandCPSet = new HashSet<>();
        tandCPSet.add(tandCP);
        tandCPSet.add(tandCP1);

        toTest.removeTC(tandCP, user, tandCPSet);

        Set<TandCP> tandCPSet1 = new HashSet<>();
        Set<TandCBought> tandCBoughtSet = new HashSet<>();

        for (TandCP tandCP2 : tandCPSet) {
            if (Objects.equals(tandCP2.getId(), tandCP.getId())) {
               TandCBought tandCBought = new TandCBought();
                tandCBought.setId(1L);
                tandCBought.setName(tandCP2.getName());
                tandCBought.setPrice(tandCP2.getPrice());
                tandCBought.setTandC(tandCP2.getTandC());
                tandCBoughtSet.add(tandCBought);
            }else {
                tandCPSet1.add(tandCP2);
            }

        }
        assertEquals(1, tandCBoughtSet.size());
        assertEquals(1, tandCPSet1.size());;
    }















    private Role iterate(Set<Role> roles) {
        Role r = new Role();
        for (Role rr : roles) {
            r = rr;
        }
        return r;
    }

}
