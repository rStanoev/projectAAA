package com.example.projecta.service;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;

public interface HardwareService {
    Hardware findByType(HardwareEnum type);
}
