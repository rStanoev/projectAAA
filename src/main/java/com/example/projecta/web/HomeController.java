package com.example.projecta.web;

import com.example.projecta.service.HardwarePService;
import com.example.projecta.service.PcPService;
import com.example.projecta.service.PeripheralPService;
import com.example.projecta.service.TandCPService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final HardwarePService hardwarePService;
    private final PeripheralPService peripheralPService;
    private final PcPService pcPService;
    private final TandCPService tandCPService;

    public HomeController(HardwarePService hardwarePService, PeripheralPService peripheralPService, PcPService pcPService, TandCPService tandCPService) {
        this.hardwarePService = hardwarePService;
        this.peripheralPService = peripheralPService;
        this.pcPService = pcPService;
        this.tandCPService = tandCPService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("lHC", hardwarePService.showHC());
        model.addAttribute("lPE", peripheralPService.showPE());
        model.addAttribute("lPC", pcPService.showPC());
        model.addAttribute("lTC", tandCPService.showTC());

        return "index";
    }
}
