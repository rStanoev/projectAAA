package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.TandCBought;
import com.example.projecta.repository.TandCBoughtRepository;
import com.example.projecta.service.TandCBoughtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TandCBoughtServiceImpl implements TandCBoughtService {

    private final TandCBoughtRepository tandCBoughtRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public TandCBoughtServiceImpl(TandCBoughtRepository tandCBoughtRepository, ModelMapper modelMapper) {
        this.tandCBoughtRepository = tandCBoughtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(TandCBought tandCBought) {
        this.tandCBoughtRepository.saveAndFlush(tandCBought);
    }
}
