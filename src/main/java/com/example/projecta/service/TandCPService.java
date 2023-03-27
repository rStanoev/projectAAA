package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.TandCbindingModel;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.TandCP;
import com.example.projecta.domain.dto.model.ShoppingCartModel;
import com.example.projecta.domain.dto.model.TCModel;
import com.example.projecta.domain.dto.model.TandCPListModel;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface TandCPService {
    void addTC(TandCbindingModel tandCBindingModel);



    TandCPListModel getProducts();

    TCModel getById(Long id);

    TandCP getById2(Long id, Principal principal);

    TandCP getById3(Long id);

    void removeTcFromDataBase(TandCP tandCP);

    void buyAllTandC(Principal principal);


    void map(TandCP tandCP);
}
