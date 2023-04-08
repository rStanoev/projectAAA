package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.repository.HardwareBoughtRepository;
import com.example.projecta.service.impl.HardwareBoughtServiceImpl;
import com.example.projecta.service.impl.HardwarePServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HardwareBoughtServiceImplTest {

    @Mock
    private HardwareBoughtRepository mockHardwareBoughtRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<HardwareBought> hardwareBoughtArgumentCaptor;


    private HardwareBoughtServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new HardwareBoughtServiceImpl(
                mockHardwareBoughtRepository,
                mockModelMapper
               );
    }

    @Test
    void test_addHardwareBought() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        HardwareBought hardwareBought = new HardwareBought();
        hardwareBought.setId(1L);
        hardwareBought.setName("intel");
        hardwareBought.setPrice(124.50);
        hardwareBought.setTime(LocalDateTime.of(2001, 11, 26, 3, 15,49));
        hardwareBought.setHardware(hardware);

        toTest.add(hardwareBought);

        Mockito.verify(mockHardwareBoughtRepository).saveAndFlush(any());
    }

}
