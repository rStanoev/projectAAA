package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.PeripheralBindingModel;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.model.PeripheralModel;
import com.example.projecta.domain.dto.model.PeripheralPListModel;
import com.example.projecta.domain.dto.model.ShoppingCartModel;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface PeripheralPService {
    void addPE(PeripheralBindingModel peripheralBindingModel);

    PeripheralPListModel getProducts();

    PeripheralModel getById(Long id);

    PeripheralP getById2(Long id, Principal principal);

    PeripheralP getById3(Long id);

    void removePeFromDataBase(PeripheralP peripheralP);

    void removeAllPeFromDataBase(Set<PeripheralP> peripheralPs);

    void buyAllPeripheral(Principal principal);


    void map(PeripheralP peripheralP);

    Set<PeripheralP> fill(Set<PeripheralP> peripherals);

    List<PeripheralP> findLast();

    List<PeripheralP> getLowestPrice();

    List<PeripheralP> showPE();
}
