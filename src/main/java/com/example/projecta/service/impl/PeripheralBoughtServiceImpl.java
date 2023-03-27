package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.PeripheralBought;
import com.example.projecta.repository.PeripheralBoughtRepository;
import com.example.projecta.service.PeripheralBoughtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeripheralBoughtServiceImpl implements PeripheralBoughtService {

    private final PeripheralBoughtRepository peripheralBoughtRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PeripheralBoughtServiceImpl(PeripheralBoughtRepository peripheralBoughtRepository, ModelMapper modelMapper) {
        this.peripheralBoughtRepository = peripheralBoughtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(PeripheralBought peripheralBought) {
        peripheralBoughtRepository.saveAndFlush(peripheralBought);
    }
}
