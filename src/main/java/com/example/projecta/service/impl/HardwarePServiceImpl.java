package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.model.HardwareModel;
import com.example.projecta.domain.dto.model.HardwarePListModel;
import com.example.projecta.repository.HardwarePRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.HardwareBoughtService;
import com.example.projecta.service.HardwarePService;
import com.example.projecta.service.HardwareService;
import com.example.projecta.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class HardwarePServiceImpl implements HardwarePService {

    private final HardwarePRepository hardwarePRepository;
    private final HardwareService hardwareService;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final UserService userService;

    private final HardwareBoughtService hardwareBoughtService;


    @Autowired
    public HardwarePServiceImpl(HardwarePRepository hardwarePRepository, HardwareService hardwareService, ModelMapper modelMapper, UserRepository userRepository, UserService userService, HardwareBoughtService hardwareBoughtService) {
        this.hardwarePRepository = hardwarePRepository;
        this.hardwareService = hardwareService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userService = userService;
        this.hardwareBoughtService = hardwareBoughtService;
    }


    @Override
    public void addHC(HardwareBindingModel hardwareBindingModel) {
        HardwareP hardwareP = modelMapper.map(hardwareBindingModel, HardwareP.class);
        hardwareP.setHardware(hardwareService.findByType(hardwareBindingModel.getType()));

        this.hardwarePRepository.saveAndFlush(hardwareP);
    }

    @Override
    public HardwarePListModel getProducts() {
        HardwarePListModel hardwarePListModel = new HardwarePListModel();

        hardwarePListModel.setCpus(this.getProductByType(HardwareEnum.CPU));
        hardwarePListModel.setGpus(this.getProductByType(HardwareEnum.GPU));
        hardwarePListModel.setMotherboards(this.getProductByType(HardwareEnum.MOTHERBOARD));
        hardwarePListModel.setHdds(this.getProductByType(HardwareEnum.HDD));
        hardwarePListModel.setSsds(this.getProductByType(HardwareEnum.SDD));
        hardwarePListModel.setPowerSuplys(this.getProductByType(HardwareEnum.POWER_SUPPLY));
        hardwarePListModel.setCases(this.getProductByType(HardwareEnum.CASE));
        hardwarePListModel.setEtc(this.getProductByType(HardwareEnum.ETC_HARDWARE));
        hardwarePListModel.setAll(this.getAllProduct());

        return hardwarePListModel;
    }

    @Override
    public HardwareModel getById(Long id) {
        HardwareP hardwareP = hardwarePRepository.findById(id).orElseThrow(null);

        return modelMapper.map(hardwareP, HardwareModel.class);
    }

    @Override
    public HardwareP getById2(Long id, Principal principal) {
        HardwareP hardwareP = hardwarePRepository.findById(id).orElseThrow(null);
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);
        Set<HardwareP> hardwarePSet = user.getHardware();
        hardwarePSet.add(hardwareP);
        user.setHardware(hardwarePSet);

        this.userRepository.saveAndFlush(user);
        return hardwareP;
    }

    @Override
    public HardwareP getById3(Long id) {
        return hardwarePRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void removeHcFromDataBase(HardwareP hardwareP) {
        this.hardwarePRepository.delete(hardwareP);
    }

    @Override
    public void buyAllHardware(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);

        Set<HardwareP> hardwarePS = user.getHardware();
        Set<HardwareBought> hardwarePS2 = user.getHardwareBought();

        if (!hardwarePS.isEmpty()) {
        for (HardwareP hardwareP1 : hardwarePS) {
                HardwareBought hardwareBought = new HardwareBought(
                        hardwareP1.getName(),
                        hardwareP1.getPrice(),
                        hardwareP1.getHardware()
                );
                LocalDateTime localDateTime = LocalDateTime.now();
                hardwareBought.setTime(localDateTime);
                hardwareBoughtService.add(hardwareBought);
                hardwarePS2.add(hardwareBought);
        }


        user.setHardwareBought(hardwarePS2);
        hardwarePS.clear();
        user.setHardware(hardwarePS);
        userRepository.saveAndFlush(user);
    }}

    @Override
    public void map(HardwareP hardwareP) {
        hardwarePRepository.saveAndFlush(hardwareP);
    }

    @Override
    public Set<HardwareP> fill(Set<HardwareP> hardware) {
        return new LinkedHashSet<>(hardware);
    }

    @Override
    public List<HardwareP> findLast() {
        return this.hardwarePRepository.findAll();
    }

    @Override
    public List<HardwareP> getLowestPrice() {
        return this.hardwarePRepository.findAllByOrderByPriceAsc();
    }


    private List<HardwareP> getAllProduct() {
        return this.hardwarePRepository.findAll();
    }

    private List<HardwareP> getProductByType(HardwareEnum type) {
        return this.hardwarePRepository.findAllByHardware_Type(type);
    }

    @Override
    public List<HardwareP> showHC() {
        List<HardwareP> hardwarePSet1 = this.hardwarePRepository.findAllByOrderByPriceAsc();
        List<HardwareP> hardwarePSet = this.hardwarePRepository.findAll();
        HardwareP hardwareP = hardwarePSet.get(hardwarePSet1.size() - 1);
        HardwareP hardwareP1 = hardwarePSet1.get(hardwarePSet1.size() - 1);
        HardwareP hardwareP2 = hardwarePSet1.get(0);

        List<HardwareP> hardwarePList = new LinkedList<>();
        hardwarePList.add(hardwareP);
        hardwarePList.add(hardwareP1);
        hardwarePList.add(hardwareP2);

        return hardwarePList;
    }
}
