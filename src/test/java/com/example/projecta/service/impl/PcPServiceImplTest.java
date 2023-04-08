package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.PcBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.domain.dto.model.PcPListModel;
import com.example.projecta.repository.PcPRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.PcBoughtService;
import com.example.projecta.service.PcService;
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
public class PcPServiceImplTest {

    @Mock
    private PcPRepository mockPcPRepository;

    @Mock
    private PcService mockPcService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserService mockUserService;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private PcBoughtService mockPcBoughtService;

    @Captor
    private ArgumentCaptor<PcP> pcPArgumentCaptor;

    private PcPServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new PcPServiceImpl(
                mockPcPRepository,
                mockPcService,
                mockModelMapper,
                mockUserService,
                mockUserRepository,
                mockPcBoughtService);

    }

    @Test
    void test_addPcP() {

        PcBindingModel pcbindingModel = new PcBindingModel();
        pcbindingModel.setName("intel");
        pcbindingModel.setPrice(120.00);
        pcbindingModel.setType(PcAndLaptopEnum.PC);
        pcbindingModel.setDescription("dadada");

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(pcbindingModel.getType());
        pc.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName(pcbindingModel.getName());
        pcP.setPrice(pcbindingModel.getPrice());
        pcP.setDescription(pcbindingModel.getDescription());
        pcP.setPc(pc);

        when(mockModelMapper.map(pcbindingModel, PcP.class)).thenReturn(pcP);

        //ACT

        toTest.addPC(pcbindingModel);

        //ASSERT

        Mockito.verify(mockPcPRepository).saveAndFlush(any());
    }

    @Test
    void test_getProducts() {

        PcPListModel pcPListModel = new PcPListModel();

        pcPListModel.setPc(test_getProductsByType(PcAndLaptopEnum.PC));
        pcPListModel.setLaptop(test_getProductsByType(PcAndLaptopEnum.LAPTOP));
        pcPListModel.setConsole(test_getProductsByType(PcAndLaptopEnum.CONSOLE));
        pcPListModel.setAll(test_ALL());

        //ACT

        toTest.getProducts();

        //ASSERT

        Assertions.assertEquals(1, pcPListModel.getPc().size());
        Assertions.assertEquals(1, pcPListModel.getLaptop().size());
        Assertions.assertEquals(2, pcPListModel.getConsole().size());
        Assertions.assertEquals(4, pcPListModel.getAll().size());


    }

    private List<PcP> test_getProductsByType(PcAndLaptopEnum type) {
        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        Pc pc1 = new Pc();
        pc1.setId(2L);
        pc1.setType(PcAndLaptopEnum.LAPTOP);
        pc1.setDescription("dadad");

        Pc pc2 = new Pc();
        pc2.setId(3L);
        pc2.setType(PcAndLaptopEnum.CONSOLE);
        pc2.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        PcP pcP1 = new PcP();
        pcP1.setId(1L);
        pcP1.setName("intel");
        pcP1.setPrice(250.8);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc1);

        PcP pcP2 = new PcP();
        pcP2.setId(1L);
        pcP2.setName("intel");
        pcP2.setPrice(250.8);
        pcP2.setDescription("fafafa");
        pcP2.setPc(pc2);

        PcP pcP3 = new PcP();
        pcP3.setId(1L);
        pcP3.setName("intel");
        pcP3.setPrice(250.8);
        pcP3.setDescription("fafafa");
        pcP3.setPc(pc2);

        List<PcP> pcPList = new ArrayList<>();
        List<PcP> pcPList1 = new ArrayList<>();

        pcPList.add(pcP);
        pcPList.add(pcP1);
        pcPList.add(pcP2);
        pcPList.add(pcP3);
        for (PcP p :  pcPList) {
            if (p.getPc().getType() == type) {
                pcPList1.add(p);
            }
        }
        return pcPList1;
    }

    private List<PcP> test_ALL() {
        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        Pc pc1 = new Pc();
        pc1.setId(2L);
        pc1.setType(PcAndLaptopEnum.LAPTOP);
        pc1.setDescription("dadad");

        Pc pc2 = new Pc();
        pc2.setId(3L);
        pc2.setType(PcAndLaptopEnum.CONSOLE);
        pc2.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        PcP pcP1 = new PcP();
        pcP1.setId(1L);
        pcP1.setName("intel");
        pcP1.setPrice(250.8);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc1);

        PcP pcP2 = new PcP();
        pcP2.setId(1L);
        pcP2.setName("intel");
        pcP2.setPrice(250.8);
        pcP2.setDescription("fafafa");
        pcP2.setPc(pc2);

        PcP pcP3 = new PcP();
        pcP3.setId(1L);
        pcP3.setName("intel");
        pcP3.setPrice(250.8);
        pcP3.setDescription("fafafa");
        pcP3.setPc(pc2);

        List<PcP> pcPList = new ArrayList<>();

        pcPList.add(pcP);
        pcPList.add(pcP1);
        pcPList.add(pcP2);
        pcPList.add(pcP3);

        return pcPList;
    }

    @Test
    void test_removePeFromDataBase() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        PcP pcP1 = new PcP();
        pcP1.setId(1L);
        pcP1.setName("intel");
        pcP1.setPrice(250.8);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc);

        List<PcP> pcPList = new LinkedList<>();
        pcPList.add(pcP);
        pcPList.add(pcP1);

        //ACT

        toTest.removePcFromDataBase(pcP);

        //ASSERT
        pcPList.remove(pcP);

        Assertions.assertEquals(1, pcPList.size());
    }

    @Test
    void test_getById3() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        PcP pcP1 = new PcP();
        pcP1.setId(2L);
        pcP1.setName("intel");
        pcP1.setPrice(250.8);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc);

        PcP pcP2 = new PcP();
        pcP2.setId(3L);
        pcP2.setName("intel");
        pcP2.setPrice(250.8);
        pcP2.setDescription("fafafa");
        pcP2.setPc(pc);

        List<PcP> pcPList = new LinkedList<>();
        List<PcP> pcPList1 = new LinkedList<>();
        pcPList.add(pcP);
        pcPList.add(pcP1);
        pcPList.add(pcP2);

        when(mockPcPRepository.findById(1L)).thenReturn(Optional.of(pcP));
        //ACT
        toTest.getById3(1L);

        //ASSERT
        for (PcP p : pcPList) {
            if (p.getId() != 1L) {
                pcPList1.add(p);
            }
        }

        Assertions.assertEquals(2, pcPList1.size());
    }

    @Test
    void buyAllPeripheral()  {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        PcP pcP1 = new PcP();
        pcP1.setId(1L);
        pcP1.setName("intel");
        pcP1.setPrice(250.8);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc);


        Set<PcP> pcPSet = new HashSet<>();
        Set<PcBought> pcBoughtSet = new HashSet<>();
        pcPSet.add(pcP);
        pcPSet.add(pcP1);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setPcs(pcPSet);

        when(mockUserRepository.findByEmail("gosho@abv.bg")).thenReturn(Optional.of(user));

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        for (PcP p : pcPSet) {
            PcBought pcBought = new PcBought();
            pcBought.setId(p.getId());
            pcBought.setName(p.getName());
            pcBought.setPrice(p.getPrice());
            pcBought.setTime(LocalDateTime.now());
            pcBought.setPc(p.getPc());
            pcBoughtSet.add(pcBought);
        }

        user.setPcsBought(pcBoughtSet);
        pcPSet.clear();
        user.setPcs(pcPSet);
        //ACT
        toTest.buyAllPc(principal);

        //ASSERT

        Assertions.assertEquals(0, pcPSet.size());
        Assertions.assertEquals(2, pcBoughtSet.size());
    }

    @Test
    void showPE()  {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        PcP pcP1 = new PcP();
        pcP1.setId(1L);
        pcP1.setName("intel");
        pcP1.setPrice(250.8);
        pcP1.setDescription("fafafa");
        pcP1.setPc(pc);

        PcP pcP2 = new PcP();
        pcP2.setId(1L);
        pcP2.setName("intel");
        pcP2.setPrice(250.8);
        pcP2.setDescription("fafafa");
        pcP2.setPc(pc);


        List<PcP> pcPList = new LinkedList<>();
        List<PcP> pcP1List = new LinkedList<>();
        pcPList.add(pcP);
        pcPList.add(pcP1);
        pcPList.add(pcP2);

        pcP1List.add(pcP);
        pcP1List.add(pcP2);
        pcP1List.add(pcP1);

        when(mockPcPRepository.findAllByOrderByPriceAsc()).thenReturn(pcP1List);

        //ACT
        toTest.showPC();

        //ASSERT

        Assertions.assertEquals(pcP2, pcPList.get(pcPList.size() - 1));
        Assertions.assertEquals(pcP1 , pcP1List.get(pcP1List.size() - 1));
        Assertions.assertEquals(pcP, pcP1List.get(0));
    }

    @Test
    void test_map() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);


        //ACT

        toTest.map(pcP);

        //ASSERT

        Mockito.verify(mockPcPRepository).saveAndFlush(any());
    }
}
