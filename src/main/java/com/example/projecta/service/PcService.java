package com.example.projecta.service;

import com.example.projecta.domain.dto.entity.Pc;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;

public interface PcService {
    Pc findByType(PcAndLaptopEnum type);
}
