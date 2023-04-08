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
import com.example.projecta.service.UserService;
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

    private final UserService userService;

    private final UserRepository userRepository;

    private final PeripheralBoughtService peripheralBoughtService;

    @Autowired
    public PeripheralPServiceImpl(PeripheralPRepository peripheralPRepository, PeripheralService peripheralService, ModelMapper modelMapper, UserService userService, UserRepository userRepository, PeripheralBoughtService peripheralBoughtService) {
        this.peripheralPRepository = peripheralPRepository;
        this.peripheralService = peripheralService;
        this.modelMapper = modelMapper;
        this.userService = userService;
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
        Set<PeripheralP> peripheralPSet = user.getPeripherals();
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

    public void removeAllPeFromDataBase(Set<PeripheralP> peripheralPs) {
        this.peripheralPRepository.deleteAll(peripheralPs);
    }

    @Override
    public void buyAllPeripheral(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);

        Set<PeripheralP> peripheralPS = user.getPeripherals();
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
        userRepository.saveAndFlush(user);
    }
}

    @Override
    public void map(PeripheralP peripheralP) {
        this.peripheralPRepository.saveAndFlush(peripheralP);
    }

    @Override
    public Set<PeripheralP> fill(Set<PeripheralP> peripherals) {
        return new LinkedHashSet<>(peripherals);
    }

    @Override
    public List<PeripheralP> findLast() {
        return this.peripheralPRepository.findAll();
    }

    @Override
    public List<PeripheralP> getLowestPrice() {
        return this.peripheralPRepository.findByOrderByPriceAsc();
    }

    @Override
    public List<PeripheralP> showPE() {
        List<PeripheralP> peripheralPList = new LinkedList<>();
        List<PeripheralP> peripheralPSet = this.peripheralPRepository.findAll();
        List<PeripheralP> peripheralPSet1 = this.peripheralPRepository.findAllByOrderByPriceAsc();
        if (peripheralPSet.size() != 0 && peripheralPSet1.size() != 0) {
        PeripheralP peripheralP = peripheralPSet.get(peripheralPSet.size() - 1);
        PeripheralP peripheralP1 = peripheralPSet1.get(peripheralPSet1.size() - 1);
        PeripheralP peripheralP2 = peripheralPSet1.get(0);


        peripheralPList.add(peripheralP);
        peripheralPList.add(peripheralP1);
        peripheralPList.add(peripheralP2);
    }
        return peripheralPList;
    }

    @Override
    public Double getSumOfAllElements(Set<PeripheralP> peripheral) {
        Double totalSum = 0.0;

        for (PeripheralP p : peripheral) {
            totalSum += p.getPrice();
        }
        return totalSum;
    }



    private List<PeripheralP> getAllProduct() {
        return peripheralPRepository.findAll();
    }

    private List<PeripheralP> getProductByType(PeripheralEnum type) {
        return peripheralPRepository.findAllByPeripheral_Type(type);
    }
}
