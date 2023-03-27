package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.TandCbindingModel;
import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.entity.TandCBought;
import com.example.projecta.domain.dto.entity.TandCP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import com.example.projecta.domain.dto.model.TCModel;
import com.example.projecta.domain.dto.model.TandCPListModel;
import com.example.projecta.repository.TandCPRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.TandCBoughtService;
import com.example.projecta.service.TandCPService;
import com.example.projecta.service.TandCService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TandCPServiceImpl implements TandCPService {

    private final TandCPRepository tandCPRepository;
    private final TandCService tandCService;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final TandCBoughtService tandCBoughtService;

    private Set<TandCP> tandCPSet = new HashSet<>();

    @Autowired
    public TandCPServiceImpl(TandCPRepository tandCPRepository, TandCService tandCService, ModelMapper modelMapper, UserRepository userRepository, TandCBoughtService tandCBoughtService) {
        this.tandCPRepository = tandCPRepository;
        this.tandCService = tandCService;

        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.tandCBoughtService = tandCBoughtService;
    }


    @Override
    public void addTC(TandCbindingModel tandCBindingModel) {
        TandCP tandCP = modelMapper.map(tandCBindingModel, TandCP.class);
        tandCP.setTandC(tandCService.findByType(tandCBindingModel.getType()));

        this.tandCPRepository.saveAndFlush(tandCP);
    }

    @Override
    public TandCPListModel getProducts() {
        TandCPListModel listModel = new TandCPListModel();

        listModel.setTable(this.getProductByType(ChairAndTableEnum.TABLE));
        listModel.setChair(this.getProductByType(ChairAndTableEnum.CHAIR));
        listModel.setAll(this.getAllTandC());


        return listModel;
    }

    @Override
    public TCModel getById(Long id) {
        TandCP tandCP = tandCPRepository.findById(id).orElseThrow(null);

        return modelMapper.map(tandCP, TCModel.class);
    }

    @Override
    public TandCP getById2(Long id , Principal principal) {
        TandCP tandCP = tandCPRepository.findById(id).orElseThrow(null);

        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);
        tandCPSet.add(tandCP);
        user.settANDcs(tandCPSet);

        this.userRepository.saveAndFlush(user);


        return tandCP;
    }

    @Override
    public TandCP getById3(Long id) {
        return tandCPRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void removeTcFromDataBase(TandCP tandCP) {
        this.tandCPRepository.delete(tandCP);
    }

    @Override
    public void buyAllTandC(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);


        Set<TandCP> tandCPS = user.gettANDcs();
        Set<TandCP> tandCPS3 = user.gettANDcs();
        Set<TandCBought> tandCBoughts = user.gettANDcsBought();

        if (!tandCPS.isEmpty()) {


        for (TandCP tandCP1 : tandCPS) {
                TandCBought tandCBought = new TandCBought(
                        tandCP1.getName(),
                        tandCP1.getPrice(),
                        tandCP1.getTandC()
                );
                tandCBought.setTime(LocalDateTime.now());
                tandCBoughtService.add(tandCBought);
                tandCBoughts.add(tandCBought);
        }

        user.settANDcsBought(tandCBoughts);
       tandCPS.clear();
       user.settANDcs(tandCPS);
            this.tandCPRepository.deleteAll(tandCPS3);
        userRepository.saveAndFlush(user);
    }
}

    @Override
    public void map(TandCP tandCP) {
        this.tandCPRepository.saveAndFlush(tandCP);
    }


    private List<TandCP> getAllTandC() {
        return tandCPRepository.findAll();
    }

    private List<TandCP> getProductByType(ChairAndTableEnum type) {
        return tandCPRepository.findAllByTandC_Type(type);
    }


}
