package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.repository.HardwareBoughtRepository;
import com.example.projecta.service.HardwareBoughtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardwareBoughtServiceImpl implements HardwareBoughtService {

    private final HardwareBoughtRepository hardwareBoughtRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HardwareBoughtServiceImpl(HardwareBoughtRepository hardwareBoughtRepository, ModelMapper modelMapper) {
        this.hardwareBoughtRepository = hardwareBoughtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(HardwareBought hardwareBought) {
        hardwareBoughtRepository.saveAndFlush(hardwareBought);
    }
}
