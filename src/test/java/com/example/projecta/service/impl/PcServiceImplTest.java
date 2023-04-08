package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.Pc;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.repository.HardwareRepository;
import com.example.projecta.repository.PcRepository;
import com.example.projecta.service.impl.HardwareServiceImpl;
import com.example.projecta.service.impl.PcServiceImpl;
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
public class PcServiceImplTest {

    @Mock
    private PcRepository mockPcRepository;


    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<Pc> pcArgumentCaptor;


    private PcServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new PcServiceImpl(
                mockPcRepository,
                mockModelMapper
        );
    }

    @Test
    void test_findByTypeHC() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.LAPTOP);
        pc.setDescription("dadad");

        Pc pc1 = new Pc();
        pc1.setId(2L);
        pc1.setType(PcAndLaptopEnum.PC);
        pc1.setDescription("dadad");


        when(mockPcRepository.findByType(PcAndLaptopEnum.LAPTOP)).thenReturn(pc);

        //ACT

        toTest.findByType(PcAndLaptopEnum.LAPTOP);

        //ASSERT
        Assertions.assertEquals(pc, toTest.findByType(PcAndLaptopEnum.LAPTOP));
        Assertions.assertNotEquals(pc1, toTest.findByType(PcAndLaptopEnum.LAPTOP));
    }
}
