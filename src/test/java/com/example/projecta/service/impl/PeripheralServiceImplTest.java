package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.Peripheral;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.repository.HardwareRepository;
import com.example.projecta.repository.PeripheralRepository;
import com.example.projecta.service.impl.HardwareServiceImpl;
import com.example.projecta.service.impl.PeripheralServiceImpl;
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
public class PeripheralServiceImplTest {

    @Mock
    private PeripheralRepository mockPeripheralRepository;


    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<Peripheral> peripheralArgumentCaptor;


    private PeripheralServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new PeripheralServiceImpl(
                mockPeripheralRepository,
                mockModelMapper
        );
    }

    @Test
    void test_findByTypeHC() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        Peripheral peripheral1 = new Peripheral();
        peripheral1.setId(2L);
        peripheral1.setType(PeripheralEnum.HEADSET);
        peripheral1.setDescription("dadad");


        when(mockPeripheralRepository.findByType(PeripheralEnum.MOUSE)).thenReturn(peripheral);

        //ACT

        toTest.findByType(PeripheralEnum.MOUSE);

        //ASSERT
        Assertions.assertEquals(peripheral, toTest.findByType(PeripheralEnum.MOUSE));
        Assertions.assertNotEquals(peripheral1, toTest.findByType(PeripheralEnum.MOUSE));
    }
}
