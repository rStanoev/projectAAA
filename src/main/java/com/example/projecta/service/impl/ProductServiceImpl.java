package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.entity.TandCP;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.model.ShoppingCartModel;
import com.example.projecta.repository.HardwarePRepository;
import com.example.projecta.repository.PcPRepository;
import com.example.projecta.repository.PeripheralPRepository;
import com.example.projecta.repository.TandCPRepository;
import com.example.projecta.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    private final PeripheralPRepository peripheralPRepository;
    private final PcPRepository pcPRepository;
    private final HardwarePRepository hardwarePRepository;
    private final TandCPRepository tandCPRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(PeripheralPRepository peripheralPRepository, PcPRepository pcPRepository, HardwarePRepository hardwarePRepository, TandCPRepository tandCPRepository, ModelMapper modelMapper) {
        this.peripheralPRepository = peripheralPRepository;
        this.pcPRepository = pcPRepository;
        this.hardwarePRepository = hardwarePRepository;
        this.tandCPRepository = tandCPRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ShoppingCartModel getByDescription(String idd, String description) {
        Long id = Long.parseLong(idd);
        if (description.equals("a table that is designed especially for gambling and that often has depressions for counters and designs painted,A gaming chair is a type of chair designed for the comfort of gamers.")) {
            TandCP tandCP = tandCPRepository.findById(id).orElseThrow(null);
            ShoppingCartModel shoppingCartModel = new ShoppingCartModel(
                    tandCP.getId(),
                    tandCP.getName(),
                    tandCP.getPrice(),
                    tandCP.getTandC()
            );
            return shoppingCartModel;
        }else if (description.equals("A peripheral device or peripheral is an auxiliary hardware device used to transfer information into and out of a computer. ")) {
            PeripheralP peripheralP = peripheralPRepository.findById(id).orElseThrow(null);
            ShoppingCartModel shoppingCartModel = new ShoppingCartModel(
                    peripheralP.getId(),
                    peripheralP.getName(),
                    peripheralP.getPrice(),
                    peripheralP.getPeripheral()
            );
            return shoppingCartModel;
        }else if (description.equals("PC stands for personal computer. It is a broad term used to describe any computing device meant for everyday, individual use. Laptops, desktops, tablets, smartphones, and other devices can all considered PCs")) {
            PcP pcP = pcPRepository.findById(id).orElseThrow(null);
            ShoppingCartModel shoppingCartModel = new ShoppingCartModel(
                    pcP.getId(),
                    pcP.getName(),
                    pcP.getPrice(),
                    pcP.getPc()
            );
            return shoppingCartModel;
        }else if (description.equals("Hardware refers to the external and internal devices and equipment that enable you to perform major functions such as input, output, storage, communication, processing, and more.")) {
            HardwareP hardwareP = hardwarePRepository.findById(id).orElseThrow(null);
            ShoppingCartModel shoppingCartModel = new ShoppingCartModel(
                    hardwareP.getId(),
                    hardwareP.getName(),
                    hardwareP.getPrice(),
                    hardwareP.getHardware()
            );
            return shoppingCartModel;
        }

    return null;
    }
}
