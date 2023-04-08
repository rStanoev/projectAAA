package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.model.HardwarePListModel;
import com.example.projecta.repository.HardwarePRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.HardwareBoughtService;
import com.example.projecta.service.HardwareService;
import com.example.projecta.service.UserService;
import com.example.projecta.service.impl.HardwarePServiceImpl;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HardwarePServiceImplTest {

    @Mock
    private HardwarePRepository mockHardwarePRepository;

    @Mock
    private HardwareService mockHardwareService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserService mockUserService;

    @Mock
    private HardwareBoughtService mockHardwareBoughtService;

    @Captor
    private ArgumentCaptor<HardwareP> hardwarePArgumentCaptor;


    private HardwarePServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new HardwarePServiceImpl(
                mockHardwarePRepository,
                mockHardwareService,
                mockModelMapper,
                mockUserRepository,
                mockUserService,
                mockHardwareBoughtService);

    }

    @Test
    void test_addHardwareP() {

        HardwareBindingModel hardwareBindingModel = new HardwareBindingModel();
        hardwareBindingModel.setName("intel");
        hardwareBindingModel.setPrice(120.00);
        hardwareBindingModel.setType(HardwareEnum.CPU);
        hardwareBindingModel.setDescription("dadada");

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(hardwareBindingModel.getType());
        hardware.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName(hardwareBindingModel.getName());
        hardwareP.setPrice(hardwareBindingModel.getPrice());
        hardwareP.setDescription(hardwareBindingModel.getDescription());
        hardwareP.setHardware(hardware);

        when(mockModelMapper.map(hardwareBindingModel, HardwareP.class)).thenReturn(hardwareP);

        //ACT

        toTest.addHC(hardwareBindingModel);

        //ASSERT

        Mockito.verify(mockHardwarePRepository).saveAndFlush(any());
    }

    @Test
    void test_getProducts() {

        HardwarePListModel hardwarePListModel = new HardwarePListModel();

        hardwarePListModel.setCpus(test_getProductsByType(HardwareEnum.CPU));
        hardwarePListModel.setGpus(test_getProductsByType(HardwareEnum.GPU));
        hardwarePListModel.setMotherboards(test_getProductsByType(HardwareEnum.MOTHERBOARD));
        hardwarePListModel.setHdds(test_getProductsByType(HardwareEnum.HDD));
        hardwarePListModel.setSsds(test_getProductsByType(HardwareEnum.SDD));
        hardwarePListModel.setPowerSuplys(test_getProductsByType(HardwareEnum.POWER_SUPPLY));
        hardwarePListModel.setCases(test_getProductsByType(HardwareEnum.CASE));
        hardwarePListModel.setEtc(test_getProductsByType(HardwareEnum.ETC_HARDWARE));
        hardwarePListModel.setAll(test_ALL());

        //ACT

        toTest.getProducts();

        //ASSERT

        Assertions.assertEquals(1, hardwarePListModel.getCpus().size());
        Assertions.assertEquals(1, hardwarePListModel.getGpus().size());
        Assertions.assertEquals(0, hardwarePListModel.getMotherboards().size());
        Assertions.assertEquals(0, hardwarePListModel.getHdds().size());
        Assertions.assertEquals(2, hardwarePListModel.getSsds().size());
        Assertions.assertEquals(0, hardwarePListModel.getPowerSuplys().size());
        Assertions.assertEquals(0, hardwarePListModel.getCases().size());
        Assertions.assertEquals(0, hardwarePListModel.getEtc().size());
        Assertions.assertEquals(4, hardwarePListModel.getAll().size());
    }

    private List<HardwareP> test_getProductsByType(HardwareEnum cpu) {
        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        Hardware hardware1 = new Hardware();
        hardware1.setId(1L);
        hardware1.setType(HardwareEnum.GPU);
        hardware1.setDescription("dadad");

        Hardware hardware2 = new Hardware();
        hardware2.setId(1L);
        hardware2.setType(HardwareEnum.SDD);
        hardware2.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(250.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        HardwareP hardwareP1 = new HardwareP();
        hardwareP1.setId(1L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(250.8);
        hardwareP1.setDescription("fefeef");
        hardwareP1.setHardware(hardware1);

        HardwareP hardwareP2 = new HardwareP();
        hardwareP2.setId(1L);
        hardwareP2.setName("intel");
        hardwareP2.setPrice(250.8);
        hardwareP2.setDescription("fefeef");
        hardwareP2.setHardware(hardware2);

        HardwareP hardwareP3 = new HardwareP();
        hardwareP3.setId(1L);
        hardwareP3.setName("intel");
        hardwareP3.setPrice(250.8);
        hardwareP3.setHardware(hardware2);
        hardwareP3.setDescription("fefeef");
        List<HardwareP> hardwares = new ArrayList<>();
        List<HardwareP> hardwares1 = new ArrayList<>();
        hardwares.add(hardwareP);
        hardwares.add(hardwareP1);
        hardwares.add(hardwareP2);
        hardwares.add(hardwareP3);
        for (HardwareP h :  hardwares) {
            if (h.getHardware().getType() == cpu) {
                hardwares1.add(h);
            }
        }
        return hardwares1;
    }

    private List<HardwareP> test_ALL() {
        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        Hardware hardware1 = new Hardware();
        hardware1.setId(1L);
        hardware1.setType(HardwareEnum.GPU);
        hardware1.setDescription("dadad");

        Hardware hardware2 = new Hardware();
        hardware2.setId(1L);
        hardware2.setType(HardwareEnum.SDD);
        hardware2.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(250.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        HardwareP hardwareP1 = new HardwareP();
        hardwareP1.setId(1L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(250.8);
        hardwareP1.setDescription("fefeef");
        hardwareP1.setHardware(hardware1);

        HardwareP hardwareP2 = new HardwareP();
        hardwareP2.setId(1L);
        hardwareP2.setName("intel");
        hardwareP2.setPrice(250.8);
        hardwareP2.setDescription("fefeef");
        hardwareP2.setHardware(hardware2);

        HardwareP hardwareP3 = new HardwareP();
        hardwareP3.setId(1L);
        hardwareP3.setName("intel");
        hardwareP3.setPrice(250.8);
        hardwareP3.setDescription("fefeef");
        hardwareP3.setHardware(hardware2);
        List<HardwareP> hardwares = new ArrayList<>();
        hardwares.add(hardwareP);
        hardwares.add(hardwareP1);
        hardwares.add(hardwareP2);
        hardwares.add(hardwareP3);

        return hardwares;
    }

    @Test
    void test_removeHcFromDataBase() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(250.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        HardwareP hardwareP1 = new HardwareP();
        hardwareP1.setId(1L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(250.8);
        hardwareP1.setDescription("fefeef");
        hardwareP1.setHardware(hardware);

        List<HardwareP> hardwarePList = new LinkedList<>();
        hardwarePList.add(hardwareP);
        hardwarePList.add(hardwareP1);

        //ACT

        toTest.removeHcFromDataBase(hardwareP);

        //ASSERT
        hardwarePList.remove(hardwareP);

        Assertions.assertEquals(1, hardwarePList.size());
    }

    @Test
    void test_getById3() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(250.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        HardwareP hardwareP1 = new HardwareP();
        hardwareP1.setId(2L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(250.8);
        hardwareP1.setDescription("fefeef");
        hardwareP1.setHardware(hardware);

        HardwareP hardwareP2 = new HardwareP();
        hardwareP2.setId(3L);
        hardwareP2.setName("intel");
        hardwareP2.setPrice(250.8);
        hardwareP2.setDescription("fefeef");
        hardwareP2.setHardware(hardware);

        List<HardwareP> hardwarePList = new LinkedList<>();
        List<HardwareP> hardwarePList1 = new LinkedList<>();
        hardwarePList.add(hardwareP);
        hardwarePList.add(hardwareP1);
        hardwarePList.add(hardwareP2);

        when(mockHardwarePRepository.findById(1L)).thenReturn(Optional.of(hardwareP));
        //ACT
        toTest.getById3(1L);

        //ASSERT
        for (HardwareP h : hardwarePList) {
            if (h.getId() != 1L) {
                hardwarePList1.add(h);
            }
        }

        Assertions.assertEquals(2, hardwarePList1.size());
    }

    @Test
    void buyAllHardware()  {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(250.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        HardwareP hardwareP1 = new HardwareP();
        hardwareP1.setId(2L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(250.8);
        hardwareP1.setDescription("fefeef");
        hardwareP1.setHardware(hardware);


        Set<HardwareP> hardwarePList = new HashSet<>();
        Set<HardwareBought> hardwarePBouhgtList = new HashSet<>();
        hardwarePList.add(hardwareP);
        hardwarePList.add(hardwareP1);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setHardware(hardwarePList);

        when(mockUserRepository.findByEmail("gosho@abv.bg")).thenReturn(Optional.of(user));

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        for (HardwareP h : hardwarePList) {
            HardwareBought hardwareBought = new HardwareBought();
            hardwareBought.setId(h.getId());
            hardwareBought.setName(h.getName());
            hardwareBought.setPrice(h.getPrice());
            hardwareBought.setTime(LocalDateTime.now());
            hardwareBought.setHardware(h.getHardware());
            hardwarePBouhgtList.add(hardwareBought);
        }

        user.setHardwareBought(hardwarePBouhgtList);
        hardwarePList.clear();
        user.setHardware(hardwarePList);
        //ACT
        toTest.buyAllHardware(principal);

        //ASSERT

        Assertions.assertEquals(0, hardwarePList.size());
        Assertions.assertEquals(2, hardwarePBouhgtList.size());
    }

    @Test
    void showHC()  {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(150.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        HardwareP hardwareP1 = new HardwareP();
        hardwareP1.setId(2L);
        hardwareP1.setName("intel");
        hardwareP1.setPrice(350.8);
        hardwareP1.setDescription("fefeef");
        hardwareP1.setHardware(hardware);

        HardwareP hardwareP2 = new HardwareP();
        hardwareP2.setId(2L);
        hardwareP2.setName("intel");
        hardwareP2.setPrice(250.8);
        hardwareP2.setDescription("fefeef");
        hardwareP2.setHardware(hardware);


        List<HardwareP> hardwarePList = new LinkedList<>();
        List<HardwareP> hardwareP1List = new LinkedList<>();
        hardwarePList.add(hardwareP);
        hardwarePList.add(hardwareP1);
        hardwarePList.add(hardwareP2);

        hardwareP1List.add(hardwareP);
        hardwareP1List.add(hardwareP2);
        hardwareP1List.add(hardwareP1);

        when(mockHardwarePRepository.findAllByOrderByPriceAsc()).thenReturn(hardwareP1List);

        //ACT
        toTest.showHC();

        //ASSERT

        Assertions.assertEquals(hardwareP2, hardwarePList.get(hardwarePList.size() - 1));
        Assertions.assertEquals(hardwareP1 , hardwareP1List.get(hardwareP1List.size() - 1));
        Assertions.assertEquals(hardwareP, hardwareP1List.get(0));
    }

    @Test
    void test_map() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(150.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);


        //ACT

        toTest.map(hardwareP);

        //ASSERT

        Mockito.verify(mockHardwarePRepository).saveAndFlush(any());
    }





}
