package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.TandC;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import com.example.projecta.repository.TandCRepository;
import com.example.projecta.service.TandCService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TandCServiceImpl implements TandCService {

    private final TandCRepository tandCRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TandCServiceImpl(TandCRepository tandCRepository, ModelMapper modelMapper) {
        this.tandCRepository = tandCRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TandC findByType(ChairAndTableEnum type) {
        return this.tandCRepository.findByType(type);
    }
}
