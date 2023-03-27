package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Peripheral;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.repository.PeripheralRepository;
import com.example.projecta.service.PeripheralService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeripheralServiceImpl implements PeripheralService {

    private final PeripheralRepository peripheralRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public PeripheralServiceImpl(PeripheralRepository peripheralRepository, ModelMapper modelMapper) {
        this.peripheralRepository = peripheralRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Peripheral findByType(PeripheralEnum type) {
        return this.peripheralRepository.findByType(type);
    }
}
