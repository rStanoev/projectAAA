package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.PcBought;
import com.example.projecta.repository.PcBoughtRepository;
import com.example.projecta.service.PcBoughtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PcBoughtServiceImpl implements PcBoughtService {

    private final PcBoughtRepository pcBoughtRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PcBoughtServiceImpl(PcBoughtRepository pcBoughtRepository, ModelMapper modelMapper) {
        this.pcBoughtRepository = pcBoughtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(PcBought pcBought) {
        this.pcBoughtRepository.saveAndFlush(pcBought);
    }
}
