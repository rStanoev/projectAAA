package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.TandCbindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import com.example.projecta.domain.dto.model.TandCPListModel;
import com.example.projecta.repository.TandCPRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.TandCBoughtService;
import com.example.projecta.service.TandCService;
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
public class TandCPServiceImplTest {

    @Mock
    private TandCPRepository mockTandCPRepository;

    @Mock
    private TandCService mockTandCService;

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private UserService mockUserService;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private TandCBoughtService mockTandCBoughtService;

    @Captor
    private ArgumentCaptor<TandCP> tandCPArgumentCaptor;

    private TandCPServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new TandCPServiceImpl(
                mockTandCPRepository,
                mockTandCService,
                mockModelMapper,
                mockUserService,
                mockUserRepository,
                mockTandCBoughtService);

    }

    @Test
    void test_addPcP() {

        TandCbindingModel tandCBindingModel = new TandCbindingModel();
        tandCBindingModel.setName("intel");
        tandCBindingModel.setPrice(120.00);
        tandCBindingModel.setType(ChairAndTableEnum.CHAIR);
        tandCBindingModel.setDescription("dadada");

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(tandCBindingModel.getType());
        tandC.setDescription("dadad");

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName(tandCBindingModel.getName());
        tandCP.setPrice(tandCBindingModel.getPrice());
        tandCP.setDescription(tandCBindingModel.getDescription());
        tandCP.setTandC(tandC);

        when(mockModelMapper.map(tandCBindingModel, TandCP.class)).thenReturn(tandCP);

        //ACT

        toTest.addTC(tandCBindingModel);

        //ASSERT

        Mockito.verify(mockTandCPRepository).saveAndFlush(any());
    }

    @Test
    void test_getProducts() {

        TandCPListModel listModel = new TandCPListModel();

        listModel.setChair(test_getProductsByType(ChairAndTableEnum.CHAIR));
        listModel.setTable(test_getProductsByType(ChairAndTableEnum.TABLE));
        listModel.setAll(test_ALL());

        //ACT

        toTest.getProducts();

        //ASSERT

        Assertions.assertEquals(1, listModel.getTable().size());
        Assertions.assertEquals(2, listModel.getChair().size());
        Assertions.assertEquals(3, listModel.getAll().size());


    }

    private List<TandCP> test_getProductsByType(ChairAndTableEnum type) {
        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandC tandC1 = new TandC();
        tandC1.setId(1L);
        tandC1.setType(ChairAndTableEnum.CHAIR);
        tandC1.setDescription("dadad");


        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        TandCP tandCP1 = new TandCP();
        tandCP1.setId(1L);
        tandCP1.setName("intel");
        tandCP1.setPrice(250.8);
        tandCP1.setDescription("fafafa");
        tandCP1.setTandC(tandC1);

        TandCP tandCP2 = new TandCP();
        tandCP2.setId(1L);
        tandCP2.setName("intel");
        tandCP2.setPrice(250.8);
        tandCP2.setDescription("fafafa");
        tandCP2.setTandC(tandC1);

        List<TandCP> tandCPList = new ArrayList<>();
        List<TandCP> tandCPList1 = new ArrayList<>();

        tandCPList.add(tandCP);
        tandCPList.add(tandCP1);
        tandCPList.add(tandCP2);

        for (TandCP t :  tandCPList) {
            if (t.getTandC().getType() == type) {
                tandCPList1.add(t);
            }
        }
        return tandCPList1;
    }

    private List<TandCP> test_ALL() {
        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandC tandC1 = new TandC();
        tandC1.setId(1L);
        tandC1.setType(ChairAndTableEnum.CHAIR);
        tandC1.setDescription("dadad");


        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        TandCP tandCP1 = new TandCP();
        tandCP1.setId(1L);
        tandCP1.setName("intel");
        tandCP1.setPrice(250.8);
        tandCP1.setDescription("fafafa");
        tandCP1.setTandC(tandC1);

        TandCP tandCP2 = new TandCP();
        tandCP2.setId(1L);
        tandCP2.setName("intel");
        tandCP2.setPrice(250.8);
        tandCP2.setDescription("fafafa");
        tandCP2.setTandC(tandC1);


        List<TandCP> tandCPList = new ArrayList<>();

        tandCPList.add(tandCP);
        tandCPList.add(tandCP1);
        tandCPList.add(tandCP2);


        return tandCPList;
    }

    @Test
    void test_removePeFromDataBase() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        TandCP tandCP1 = new TandCP();
        tandCP1.setId(1L);
        tandCP1.setName("intel");
        tandCP1.setPrice(250.8);
        tandCP1.setDescription("fafafa");
        tandCP1.setTandC(tandC);

        List<TandCP> tandCPList = new LinkedList<>();
        tandCPList.add(tandCP);
        tandCPList.add(tandCP1);

        //ACT

        toTest.removeTcFromDataBase(tandCP);

        //ASSERT
        tandCPList.remove(tandCP);

        Assertions.assertEquals(1, tandCPList.size());
    }

    @Test
    void test_getById3() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        TandCP tandCP1 = new TandCP();
        tandCP1.setId(2L);
        tandCP1.setName("intel");
        tandCP1.setPrice(250.8);
        tandCP1.setDescription("fafafa");
        tandCP1.setTandC(tandC);

        TandCP tandCP2 = new TandCP();
        tandCP2.setId(3L);
        tandCP2.setName("intel");
        tandCP2.setPrice(250.8);
        tandCP2.setDescription("fafafa");
        tandCP2.setTandC(tandC);

        List<TandCP> tandCPList = new LinkedList<>();
        List<TandCP> tandCPList1 = new LinkedList<>();
        tandCPList.add(tandCP);
        tandCPList.add(tandCP1);
        tandCPList.add(tandCP2);

        when(mockTandCPRepository.findById(1L)).thenReturn(Optional.of(tandCP));
        //ACT
        toTest.getById3(1L);

        //ASSERT
        for (TandCP t : tandCPList) {
            if (t.getId() != 1L) {
                tandCPList1.add(t);
            }
        }

        Assertions.assertEquals(2, tandCPList1.size());
    }

    @Test
    void buyAllPeripheral()  {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        TandCP tandCP1 = new TandCP();
        tandCP1.setId(2L);
        tandCP1.setName("intel");
        tandCP1.setPrice(250.8);
        tandCP1.setDescription("fafafa");
        tandCP1.setTandC(tandC);


        Set<TandCP> tandCPSet = new HashSet<>();
        Set<TandCBought> tandCBoughtSet = new HashSet<>();
        tandCPSet.add(tandCP);
        tandCPSet.add(tandCP1);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.settANDcs(tandCPSet);

        when(mockUserRepository.findByEmail("gosho@abv.bg")).thenReturn(Optional.of(user));

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        for (TandCP t : tandCPSet) {
            TandCBought tandCBought = new TandCBought();
            tandCBought.setId(t.getId());
            tandCBought.setName(t.getName());
            tandCBought.setPrice(t.getPrice());
            tandCBought.setTime(LocalDateTime.now());
            tandCBought.setTandC(t.getTandC());
            tandCBoughtSet.add(tandCBought);
        }

        user.settANDcsBought(tandCBoughtSet);
        tandCPSet.clear();
        user.settANDcs(tandCPSet);
        //ACT
        toTest.buyAllTandC(principal);

        //ASSERT

        Assertions.assertEquals(0, tandCPSet.size());
        Assertions.assertEquals(2, tandCBoughtSet.size());
    }

    @Test
    void showPE()  {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        TandCP tandCP1 = new TandCP();
        tandCP1.setId(2L);
        tandCP1.setName("intel");
        tandCP1.setPrice(250.8);
        tandCP1.setDescription("fafafa");
        tandCP1.setTandC(tandC);

        TandCP tandCP2 = new TandCP();
        tandCP2.setId(2L);
        tandCP2.setName("intel");
        tandCP2.setPrice(250.8);
        tandCP2.setDescription("fafafa");
        tandCP2.setTandC(tandC);



        List<TandCP> tandCPList = new LinkedList<>();
        List<TandCP> tandCP1List = new LinkedList<>();
        tandCPList.add(tandCP);
        tandCPList.add(tandCP1);
        tandCPList.add(tandCP2);

        tandCP1List.add(tandCP);
        tandCP1List.add(tandCP2);
        tandCP1List.add(tandCP1);

        when(mockTandCPRepository.findAllByOrderByPriceAsc()).thenReturn(tandCPList);

        //ACT
        toTest.showTC();

        //ASSERT

        Assertions.assertEquals(tandCP2, tandCPList.get(tandCPList.size() - 1));
        Assertions.assertEquals(tandCP1 , tandCP1List.get(tandCP1List.size() - 1));
        Assertions.assertEquals(tandCP, tandCP1List.get(0));
    }

    @Test
    void test_map() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);


        //ACT

        toTest.map(tandCP);

        //ASSERT

        Mockito.verify(mockTandCPRepository).saveAndFlush(any());
    }
}
