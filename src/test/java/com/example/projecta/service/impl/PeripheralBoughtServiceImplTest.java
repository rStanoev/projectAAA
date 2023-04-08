package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.domain.dto.entity.Peripheral;
import com.example.projecta.domain.dto.entity.PeripheralBought;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.repository.HardwareBoughtRepository;
import com.example.projecta.repository.PeripheralBoughtRepository;
import com.example.projecta.service.impl.HardwareBoughtServiceImpl;
import com.example.projecta.service.impl.PeripheralBoughtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PeripheralBoughtServiceImplTest {

    @Mock
    private PeripheralBoughtRepository mockPeripheralBoughtRepository;

    @Mock
    private ModelMapper mockModelMapper;

    private PeripheralBoughtServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new PeripheralBoughtServiceImpl(
                mockPeripheralBoughtRepository,
                mockModelMapper
        );
    }

    @Test
    void test_addHardwareBought() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.MOUSE);
        peripheral.setDescription("dadad");

        PeripheralBought peripheralBought = new PeripheralBought();
        peripheralBought.setId(1L);
        peripheralBought.setName("intel");
        peripheralBought.setPrice(124.50);
        peripheralBought.setTime(LocalDateTime.of(2001, 11, 26, 3, 15,49));
        peripheralBought.setPeripheral(peripheral);

        toTest.add(peripheralBought);

        Mockito.verify(mockPeripheralBoughtRepository).saveAndFlush(any());
    }
}
