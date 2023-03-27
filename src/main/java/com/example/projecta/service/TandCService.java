package com.example.projecta.service;

import com.example.projecta.domain.dto.entity.TandC;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;

public interface TandCService {
    TandC findByType(ChairAndTableEnum type);
}
