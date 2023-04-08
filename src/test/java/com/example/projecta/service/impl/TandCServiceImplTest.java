package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.TandC;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.repository.HardwareRepository;
import com.example.projecta.repository.TandCRepository;
import com.example.projecta.service.impl.HardwareServiceImpl;
import com.example.projecta.service.impl.TandCServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TandCServiceImplTest {

    @Mock
    private TandCRepository mockTandCRepository;


    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<TandC> tandCArgumentCaptor;


    private TandCServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new TandCServiceImpl(
                mockTandCRepository,
                mockModelMapper
        );
    }

    @Test
    void test_findByTypeHC() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandC tandC1 = new TandC();
        tandC1.setId(2L);
        tandC1.setType(ChairAndTableEnum.CHAIR);
        tandC1.setDescription("dadad");


        when(mockTandCRepository.findByType(ChairAndTableEnum.TABLE)).thenReturn(tandC);

        //ACT

        toTest.findByType(ChairAndTableEnum.TABLE);

        //ASSERT
        Assertions.assertEquals(tandC, toTest.findByType(ChairAndTableEnum.TABLE));
        Assertions.assertNotEquals(tandC1, toTest.findByType(ChairAndTableEnum.TABLE));
    }
}
