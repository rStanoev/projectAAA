package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.Pc;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.repository.PcRepository;
import com.example.projecta.service.PcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PcServiceImpl implements PcService {

    private final PcRepository pcRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PcServiceImpl(PcRepository pcRepository, ModelMapper modelMapper) {
        this.pcRepository = pcRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Pc findByType(PcAndLaptopEnum type) {
        return this.pcRepository.findByType(type);
    }
}
