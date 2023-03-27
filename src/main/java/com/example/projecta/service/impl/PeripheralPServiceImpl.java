package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.PeripheralBindingModel;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.PeripheralBought;
import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.domain.dto.model.PeripheralModel;
import com.example.projecta.domain.dto.model.PeripheralPListModel;
import com.example.projecta.repository.PeripheralPRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.PeripheralBoughtService;
import com.example.projecta.service.PeripheralPService;
import com.example.projecta.service.PeripheralService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PeripheralPServiceImpl implements PeripheralPService {

    private final PeripheralPRepository peripheralPRepository;
    private final PeripheralService peripheralService;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final PeripheralBoughtService peripheralBoughtService;

    private Set<PeripheralP> peripheralPSet = new HashSet<>();

    @Autowired
    public PeripheralPServiceImpl(PeripheralPRepository peripheralPRepository, PeripheralService peripheralService, ModelMapper modelMapper, UserRepository userRepository, PeripheralBoughtService peripheralBoughtService) {
        this.peripheralPRepository = peripheralPRepository;
        this.peripheralService = peripheralService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.peripheralBoughtService = peripheralBoughtService;
    }


    @Override
    public void addPE(PeripheralBindingModel peripheralBindingModel) {
        PeripheralP peripheralP = modelMapper.map(peripheralBindingModel, PeripheralP.class);
        peripheralP.setPeripheral(peripheralService.findByType(peripheralBindingModel.getType()));

        this.peripheralPRepository.saveAndFlush(peripheralP);
    }

    @Override
    public PeripheralPListModel getProducts() {
        PeripheralPListModel peripheralPListModel = new PeripheralPListModel();

        peripheralPListModel.setKeyboards(this.getProductByType(PeripheralEnum.KEYBOARD));
        peripheralPListModel.setMouses(this.getProductByType(PeripheralEnum.MOUSE));
        peripheralPListModel.setHeadsets(this.getProductByType(PeripheralEnum.HEADSET));
        peripheralPListModel.setMonitors(this.getProductByType(PeripheralEnum.MONITOR));
        peripheralPListModel.setSpeakers(this.getProductByType(PeripheralEnum.SPEAKERS));
        peripheralPListModel.setControllers(this.getProductByType(PeripheralEnum.CONTROLLER));
        peripheralPListModel.setEtc(this.getProductByType(PeripheralEnum.ETC_PERIPHERAL));
        peripheralPListModel.setAll(this.getAllProduct());


        return peripheralPListModel;
    }

    @Override
    public PeripheralModel getById(Long id) {
        PeripheralP peripheralP = peripheralPRepository.findById(id).orElseThrow(null);

        return modelMapper.map(peripheralP, PeripheralModel.class);
    }

    @Override
    public PeripheralP getById2(Long id, Principal principal) {
        PeripheralP peripheralP = peripheralPRepository.findById(id).orElseThrow(null);

        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);
        peripheralPSet.add(peripheralP);
        user.setPeripherals(peripheralPSet);

        this.userRepository.saveAndFlush(user);
        return peripheralP;
    }

    @Override
    public PeripheralP getById3(Long id) {
        return peripheralPRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void removePeFromDataBase(PeripheralP peripheralP) {
        this.peripheralPRepository.delete(peripheralP);
    }

    @Override
    public void buyAllPeripheral(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);

        Set<PeripheralP> peripheralPS = user.getPeripherals();
        Set<PeripheralP> peripheralPS3 = user.getPeripherals();
        Set<PeripheralBought> peripheralPSS = user.getPeripheralsBought();

        if (!peripheralPS.isEmpty()) {
        for (PeripheralP peripheralP1 : peripheralPS) {
            PeripheralBought peripheralBought = new PeripheralBought(
                    peripheralP1.getName(),
                    peripheralP1.getPrice(),
                    peripheralP1.getPeripheral()
            );
                peripheralBought.setTime(LocalDateTime.now());
                peripheralBoughtService.add(peripheralBought);
                peripheralPSS.add(peripheralBought);
        }

        user.setPeripheralsBought(peripheralPSS);
        peripheralPS.clear();
        user.setPeripherals(peripheralPS);
        this.peripheralPRepository.deleteAll(peripheralPS3);
        userRepository.saveAndFlush(user);
    }
}

    @Override
    public void map(PeripheralP peripheralP) {
        this.peripheralPRepository.saveAndFlush(peripheralP);
    }


    private List<PeripheralP> getAllProduct() {
        return peripheralPRepository.findAll();
    }

    private List<PeripheralP> getProductByType(PeripheralEnum type) {
        return peripheralPRepository.findAllByPeripheral_Type(type);
    }
}
