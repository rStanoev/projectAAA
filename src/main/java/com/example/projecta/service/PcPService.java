package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.PcBindingModel;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.model.PcModel;
import com.example.projecta.domain.dto.model.PcPListModel;
import com.example.projecta.domain.dto.model.ShoppingCartModel;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface PcPService {
    void addPC(PcBindingModel pcbindingModel);

    PcPListModel getProducts();

    PcModel getById(Long id);

    PcP getById2(Long id, Principal principal);

    PcP getById3(Long id);

    void removePcFromDataBase(PcP pcP);

    void buyAllPc(Principal principal);


    void map(PcP pcP);

    Set<PcP> fill(Set<PcP> pcs);

    List<PcP> findLast();

    List<PcP> getLowestPrice();

    List<PcP> showPC();

    Double getSumOfAllElements(Set<PcP> pc);


}
