package com.example.projecta.service;

import com.example.projecta.domain.dto.entity.Peripheral;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;

public interface PeripheralService {
    Peripheral findByType(PeripheralEnum type);
}
