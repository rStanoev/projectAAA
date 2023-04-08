package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.repository.HardwareBoughtRepository;
import com.example.projecta.repository.TandCBoughtRepository;
import com.example.projecta.service.impl.HardwareBoughtServiceImpl;
import com.example.projecta.service.impl.TandCBoughtServiceImpl;
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
public class TandCBoughtServiceImplTest {

    @Mock
    private TandCBoughtRepository mockTandCBoughtRepository;

    @Mock
    private ModelMapper mockModelMapper;


    private TandCBoughtServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new TandCBoughtServiceImpl(
                mockTandCBoughtRepository,
                mockModelMapper
        );
    }

    @Test
    void test_addHardwareBought() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

        TandCBought tandCBought = new TandCBought();
        tandCBought.setId(1L);
        tandCBought.setName("intel");
        tandCBought.setPrice(124.50);
        tandCBought.setTime(LocalDateTime.of(2001, 11, 26, 3, 15,49));
        tandCBought.setTandC(tandC);

        toTest.add(tandCBought);

        Mockito.verify(mockTandCBoughtRepository).saveAndFlush(any());
    }
}
