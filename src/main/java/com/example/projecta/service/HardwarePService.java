package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.model.HardwareModel;
import com.example.projecta.domain.dto.model.HardwarePListModel;
import com.example.projecta.domain.dto.model.ShoppingCartModel;

import java.security.Principal;
import java.util.Set;

public interface HardwarePService {
    void addHC(HardwareBindingModel hardwareBindingModel);

    HardwarePListModel getProducts();

    HardwareModel getById(Long id);

    HardwareP getById2(Long id, Principal principal);

    HardwareP getById3(Long id);

    void removeHcFromDataBase(HardwareP hardwareP);

    void buyAllHardware(Principal principal);


    void map(HardwareP hardwareP);
}
