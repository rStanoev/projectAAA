package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.PcBindingModel;
import com.example.projecta.domain.dto.entity.PcBought;
import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.domain.dto.model.PcModel;
import com.example.projecta.domain.dto.model.PcPListModel;
import com.example.projecta.repository.PcPRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.PcBoughtService;
import com.example.projecta.service.PcPService;
import com.example.projecta.service.PcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PcPServiceImpl implements PcPService {

    private final PcPRepository pcPRepository;
    private final PcService pcService;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final PcBoughtService pcBoughtService;




    @Autowired
    public PcPServiceImpl(PcPRepository pcPRepository, PcService pcService, ModelMapper modelMapper, UserRepository userRepository, PcBoughtService pcBoughtService) {
        this.pcPRepository = pcPRepository;
        this.pcService = pcService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.pcBoughtService = pcBoughtService;
    }

    @Override
    public void addPC(PcBindingModel pcbindingModel) {
        PcP pcP = modelMapper.map(pcbindingModel, PcP.class);
        pcP.setPc(pcService.findByType(pcbindingModel.getType()));
        this.pcPRepository.saveAndFlush(pcP);
    }

    @Override
    public PcPListModel getProducts() {
        PcPListModel pcPListModel = new PcPListModel();

        pcPListModel.setPc(this.getProductByType(PcAndLaptopEnum.PC));
        pcPListModel.setLaptop(this.getProductByType(PcAndLaptopEnum.LAPTOP));
        pcPListModel.setConsole(this.getProductByType(PcAndLaptopEnum.CONSOLE));
        pcPListModel.setAll(this.getAllProduct());

        return pcPListModel;
    }

    @Override
    public PcModel getById(Long id) {
        PcP pcP = pcPRepository.findById(id).orElseThrow(null);

        return modelMapper.map(pcP, PcModel.class);
    }

    @Override
    public PcP getById2(Long id, Principal principal) {
        PcP pcP = pcPRepository.findById(id).orElseThrow(null);

        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);
        Set<PcP> pcPSet = user.getPcs();
        pcPSet.add(pcP);
        user.setPcs(pcPSet);

        this.userRepository.saveAndFlush(user);

        return pcP;
    }

    @Override
    public PcP getById3(Long id) {
        return pcPRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void removePcFromDataBase(PcP pcP) {
        this.pcPRepository.delete(pcP);
    }

    @Override
    public void buyAllPc(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(null);

        Set<PcP> pcPS = user.getPcs();
        Set<PcBought> pcBoughts = user.getPcsBought();

        if (!pcPS.isEmpty()) {
        for (PcP pcP1 : pcPS) {
                PcBought pcBought = new PcBought(
                        pcP1.getName(),
                        pcP1.getPrice(),
                        pcP1.getPc()
                );
                pcBought.setTime(LocalDateTime.now());
                pcBoughtService.add(pcBought);
            pcBoughts.add(pcBought);
        }

        user.setPcsBought(pcBoughts);
        pcPS.clear();
        user.setPcs(pcPS);
        userRepository.saveAndFlush(user);
    }
    }

    @Override
    public void map(PcP pcP) {
        this.pcPRepository.saveAndFlush(pcP);
    }

    @Override
    public Set<PcP> fill(Set<PcP> pcs) {
        return new LinkedHashSet<>(pcs);
    }

    @Override
    public List<PcP> findLast() {
        return this.pcPRepository.findAll();
    }

    @Override
    public List<PcP> getLowestPrice() {
        return this.pcPRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<PcP> showPC() {
        List<PcP> pcPSet = this.pcPRepository.findAll();
        List<PcP> pcPSet1 = this.pcPRepository.findAllByOrderByPriceAsc();
        PcP pcP = pcPSet.get(pcPSet.size() - 1);
        PcP pcP1 = pcPSet1.get(pcPSet1.size() - 1);
        PcP pcP2 = pcPSet1.get(0);

        List<PcP> pcPList = new LinkedList<>();
        pcPList.add(pcP);
        pcPList.add(pcP1);
        pcPList.add(pcP2);

        return pcPList;
    }


    private List<PcP> getAllProduct() {
       return this.pcPRepository.findAll();
    }

    private List<PcP> getProductByType(PcAndLaptopEnum type) {
        return pcPRepository.findAllByPc_Type(type);
    }




}
