package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.repository.HardwareRepository;
import com.example.projecta.service.HardwareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HardwareServiceImpl(HardwareRepository hardwareRepository, ModelMapper modelMapper) {
        this.hardwareRepository = hardwareRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Hardware findByType(HardwareEnum type) {
        return this.hardwareRepository.findByType(type);
    }
}
