package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.repository.HardwarePRepository;
import com.example.projecta.repository.HardwareRepository;
import com.example.projecta.service.impl.HardwarePServiceImpl;
import com.example.projecta.service.impl.HardwareServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HardwareServiceImplTest {

    @Mock
    private HardwareRepository mockHardwareRepository;


    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<Hardware> hardwareArgumentCaptor;


    private HardwareServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new HardwareServiceImpl(
                mockHardwareRepository,
                mockModelMapper
                );
    }

    @Test
    void test_findByTypeHC() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        Hardware hardware1 = new Hardware();
        hardware1.setId(2L);
        hardware1.setType(HardwareEnum.GPU);
        hardware1.setDescription("dadad");


        when(mockHardwareRepository.findByType(HardwareEnum.CPU)).thenReturn(hardware);

        //ACT

        toTest.findByType(HardwareEnum.CPU);

        //ASSERT
        Assertions.assertEquals(hardware, toTest.findByType(HardwareEnum.CPU));
        Assertions.assertNotEquals(hardware1, toTest.findByType(HardwareEnum.CPU));
    }

}
