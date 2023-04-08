package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.domain.dto.entity.Pc;
import com.example.projecta.domain.dto.entity.PcBought;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.repository.HardwareBoughtRepository;
import com.example.projecta.repository.PcBoughtRepository;
import com.example.projecta.service.impl.HardwareBoughtServiceImpl;
import com.example.projecta.service.impl.PcBoughtServiceImpl;
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
public class PcBoughtServiceImplTest {

    @Mock
    private PcBoughtRepository mockPcBoughtRepository;

    @Mock
    private ModelMapper mockModelMapper;


    private PcBoughtServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new PcBoughtServiceImpl(
                mockPcBoughtRepository,
                mockModelMapper
        );
    }

    @Test
    void test_addHardwareBought() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

        PcBought pcBought = new PcBought();
        pcBought.setId(1L);
        pcBought.setName("intel");
        pcBought.setPrice(124.50);
        pcBought.setTime(LocalDateTime.of(2001, 11, 26, 3, 15,49));
        pcBought.setPc(pc);

        toTest.add(pcBought);

        Mockito.verify(mockPcBoughtRepository).saveAndFlush(any());
    }
}
