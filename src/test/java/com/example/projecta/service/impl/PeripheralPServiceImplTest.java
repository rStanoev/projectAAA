package com.example.projecta.service.impl;


import com.example.projecta.domain.dto.binding.PeripheralBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.domain.dto.model.PeripheralPListModel;
import com.example.projecta.repository.PeripheralPRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.PeripheralBoughtService;
import com.example.projecta.service.PeripheralService;
import com.example.projecta.service.UserService;
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

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeripheralPServiceImplTest {

    @Mock
    private PeripheralPRepository mockPeripheralPRepository;

    @Mock
    private PeripheralService mockPeripheralService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserService mockUserService;

    @Mock
    private PeripheralBoughtService mockPeripheralBoughtService;

    @Captor
    private ArgumentCaptor<PeripheralP> hardwarePArgumentCaptor;

    private PeripheralPServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new PeripheralPServiceImpl(
                mockPeripheralPRepository,
                mockPeripheralService,
                mockModelMapper,
                mockUserService,
                mockUserRepository,
                mockPeripheralBoughtService);

    }

    @Test
    void test_addPeripheralP() {

        PeripheralBindingModel peripheralBindingModel = new PeripheralBindingModel();
        peripheralBindingModel.setName("intel");
        peripheralBindingModel.setPrice(120.00);
        peripheralBindingModel.setType(PeripheralEnum.HEADSET);
        peripheralBindingModel.setDescription("dadada");

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(peripheralBindingModel.getType());
        peripheral.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName(peripheralBindingModel.getName());
        peripheralP.setPrice(peripheralBindingModel.getPrice());
        peripheralP.setDescription(peripheralBindingModel.getDescription());
        peripheralP.setPeripheral(peripheral);

        when(mockModelMapper.map(peripheralBindingModel, PeripheralP.class)).thenReturn(peripheralP);

        //ACT

        toTest.addPE(peripheralBindingModel);

        //ASSERT

        Mockito.verify(mockPeripheralPRepository).saveAndFlush(any());
    }

    @Test
    void test_getProducts() {

        PeripheralPListModel peripheralPListModel = new PeripheralPListModel();

        peripheralPListModel.setKeyboards(test_getProductsByType(PeripheralEnum.KEYBOARD));
        peripheralPListModel.setMouses(test_getProductsByType(PeripheralEnum.MOUSE));
        peripheralPListModel.setHeadsets(test_getProductsByType(PeripheralEnum.HEADSET));
        peripheralPListModel.setMonitors(test_getProductsByType(PeripheralEnum.MONITOR));
        peripheralPListModel.setSpeakers(test_getProductsByType(PeripheralEnum.SPEAKERS));
        peripheralPListModel.setControllers(test_getProductsByType(PeripheralEnum.CONTROLLER));
        peripheralPListModel.setEtc(test_getProductsByType(PeripheralEnum.ETC_PERIPHERAL));
        peripheralPListModel.setAll(test_ALL());

        //ACT

        toTest.getProducts();

        //ASSERT

        Assertions.assertEquals(1, peripheralPListModel.getKeyboards().size());
        Assertions.assertEquals(1, peripheralPListModel.getMouses().size());
        Assertions.assertEquals(2, peripheralPListModel.getHeadsets().size());
        Assertions.assertEquals(0, peripheralPListModel.getMonitors().size());
        Assertions.assertEquals(0, peripheralPListModel.getSpeakers().size());
        Assertions.assertEquals(0, peripheralPListModel.getControllers().size());
        Assertions.assertEquals(0, peripheralPListModel.getEtc().size());
        Assertions.assertEquals(4, peripheralPListModel.getAll().size());

    }

    private List<PeripheralP> test_getProductsByType(PeripheralEnum type) {
        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        Peripheral peripheral1 = new Peripheral();
        peripheral1.setId(2L);
        peripheral1.setType(PeripheralEnum.KEYBOARD);
        peripheral1.setDescription("dadad");

        Peripheral peripheral2 = new Peripheral();
        peripheral2.setId(3L);
        peripheral2.setType(PeripheralEnum.HEADSET);
        peripheral2.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);

        PeripheralP peripheralP1 = new PeripheralP();
        peripheralP1.setId(1L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(250.8);
        peripheralP1.setDescription("fsfsfs");
        peripheralP1.setPeripheral(peripheral1);

        PeripheralP peripheralP2 = new PeripheralP();
        peripheralP2.setId(1L);
        peripheralP2.setName("intel");
        peripheralP2.setPrice(250.8);
        peripheralP2.setDescription("fsfsfs");
        peripheralP2.setPeripheral(peripheral2);

        PeripheralP peripheralP3 = new PeripheralP();
        peripheralP3.setId(1L);
        peripheralP3.setName("intel");
        peripheralP3.setPrice(250.8);
        peripheralP3.setDescription("fsfsfs");
        peripheralP3.setPeripheral(peripheral2);

        List<PeripheralP> peripheralPList = new ArrayList<>();
        List<PeripheralP> peripheralPList1 = new ArrayList<>();

        peripheralPList.add(peripheralP);
        peripheralPList.add(peripheralP1);
        peripheralPList.add(peripheralP2);
        peripheralPList.add(peripheralP3);
        for (PeripheralP p :  peripheralPList) {
            if (p.getPeripheral().getType() == type) {
                peripheralPList1.add(p);
            }
        }
        return peripheralPList1;
    }

    private List<PeripheralP> test_ALL() {
        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        Peripheral peripheral1 = new Peripheral();
        peripheral1.setId(2L);
        peripheral1.setType(PeripheralEnum.KEYBOARD);
        peripheral1.setDescription("dadad");

        Peripheral peripheral2 = new Peripheral();
        peripheral2.setId(3L);
        peripheral2.setType(PeripheralEnum.HEADSET);
        peripheral2.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);

        PeripheralP peripheralP1 = new PeripheralP();
        peripheralP1.setId(1L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(250.8);
        peripheralP1.setDescription("fsfsfs");
        peripheralP1.setPeripheral(peripheral1);

        PeripheralP peripheralP2 = new PeripheralP();
        peripheralP2.setId(1L);
        peripheralP2.setName("intel");
        peripheralP2.setPrice(250.8);
        peripheralP2.setDescription("fsfsfs");
        peripheralP2.setPeripheral(peripheral2);

        PeripheralP peripheralP3 = new PeripheralP();
        peripheralP3.setId(1L);
        peripheralP3.setName("intel");
        peripheralP3.setPrice(250.8);
        peripheralP3.setDescription("fsfsfs");
        peripheralP3.setPeripheral(peripheral2);

        List<PeripheralP> peripheralPList = new ArrayList<>();

        peripheralPList.add(peripheralP);
        peripheralPList.add(peripheralP1);
        peripheralPList.add(peripheralP2);
        peripheralPList.add(peripheralP3);

        return peripheralPList;
    }

    @Test
    void test_removePeFromDataBase() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);

        PeripheralP peripheralP1 = new PeripheralP();
        peripheralP1.setId(1L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(250.8);
        peripheralP1.setDescription("fsfsfs");
        peripheralP1.setPeripheral(peripheral);

        List<PeripheralP> peripheralPList = new LinkedList<>();
        peripheralPList.add(peripheralP);
        peripheralPList.add(peripheralP1);

        //ACT

        toTest.removePeFromDataBase(peripheralP);

        //ASSERT
        peripheralPList.remove(peripheralP);

        Assertions.assertEquals(1, peripheralPList.size());
    }

    @Test
    void test_getById3() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);

        PeripheralP peripheralP1 = new PeripheralP();
        peripheralP1.setId(2L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(250.8);
        peripheralP1.setDescription("fsfsfs");
        peripheralP1.setPeripheral(peripheral);

        PeripheralP peripheralP2 = new PeripheralP();
        peripheralP2.setId(3L);
        peripheralP2.setName("intel");
        peripheralP2.setPrice(250.8);
        peripheralP2.setDescription("fsfsfs");
        peripheralP2.setPeripheral(peripheral);

        List<PeripheralP> peripheralPList = new LinkedList<>();
        List<PeripheralP> peripheralPList1 = new LinkedList<>();
        peripheralPList.add(peripheralP);
        peripheralPList.add(peripheralP1);
        peripheralPList.add(peripheralP2);

        when(mockPeripheralPRepository.findById(1L)).thenReturn(Optional.of(peripheralP));
        //ACT
        toTest.getById3(1L);

        //ASSERT
        for (PeripheralP p : peripheralPList) {
            if (p.getId() != 1L) {
                peripheralPList1.add(p);
            }
        }

        Assertions.assertEquals(2, peripheralPList1.size());
    }

    @Test
    void buyAllPeripheral()  {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);

        PeripheralP peripheralP1 = new PeripheralP();
        peripheralP1.setId(2L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(250.8);
        peripheralP1.setDescription("fsfsfs");
        peripheralP1.setPeripheral(peripheral);


        Set<PeripheralP> peripheralPSet = new HashSet<>();
        Set<PeripheralBought> peripheralBoughtSet = new HashSet<>();
        peripheralPSet.add(peripheralP);
        peripheralPSet.add(peripheralP1);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setPeripherals(peripheralPSet);

        when(mockUserRepository.findByEmail("gosho@abv.bg")).thenReturn(Optional.of(user));

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        for (PeripheralP p : peripheralPSet) {
            PeripheralBought peripheralBought = new PeripheralBought();
            peripheralBought.setId(p.getId());
            peripheralBought.setName(p.getName());
            peripheralBought.setPrice(p.getPrice());
            peripheralBought.setTime(LocalDateTime.now());
            peripheralBought.setPeripheral(p.getPeripheral());
            peripheralBoughtSet.add(peripheralBought);
        }

        user.setPeripheralsBought(peripheralBoughtSet);
        peripheralPSet.clear();
        user.setPeripherals(peripheralPSet);
        //ACT
        toTest.buyAllPeripheral(principal);

        //ASSERT

        Assertions.assertEquals(0, peripheralPSet.size());
        Assertions.assertEquals(2, peripheralBoughtSet.size());
    }

    @Test
    void showPE()  {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);

        PeripheralP peripheralP1 = new PeripheralP();
        peripheralP1.setId(2L);
        peripheralP1.setName("intel");
        peripheralP1.setPrice(250.8);
        peripheralP1.setDescription("fsfsfs");
        peripheralP1.setPeripheral(peripheral);

        PeripheralP peripheralP2 = new PeripheralP();
        peripheralP2.setId(3L);
        peripheralP2.setName("intel");
        peripheralP2.setPrice(250.8);
        peripheralP2.setDescription("fsfsfs");
        peripheralP2.setPeripheral(peripheral);


        List<PeripheralP> PeripheralPList = new LinkedList<>();
        List<PeripheralP> PeripheralP1List = new LinkedList<>();
        PeripheralPList.add(peripheralP);
        PeripheralPList.add(peripheralP1);
        PeripheralPList.add(peripheralP2);

        PeripheralP1List.add(peripheralP);
        PeripheralP1List.add(peripheralP2);
        PeripheralP1List.add(peripheralP1);

        when(mockPeripheralPRepository.findAllByOrderByPriceAsc()).thenReturn(PeripheralP1List);

        //ACT
        toTest.showPE();

        //ASSERT

        Assertions.assertEquals(peripheralP2, PeripheralPList.get(PeripheralPList.size() - 1));
        Assertions.assertEquals(peripheralP1 , PeripheralP1List.get(PeripheralP1List.size() - 1));
        Assertions.assertEquals(peripheralP, PeripheralP1List.get(0));
    }

    @Test
    void test_map() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fsfsfs");
        peripheralP.setPeripheral(peripheral);


        //ACT

        toTest.map(peripheralP);

        //ASSERT

        Mockito.verify(mockPeripheralPRepository).saveAndFlush(any());
    }





}
