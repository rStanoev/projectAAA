package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.binding.PcBindingModel;
import com.example.projecta.domain.dto.binding.PeripheralBindingModel;
import com.example.projecta.domain.dto.binding.TandCbindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.model.ShoppingCartModel;
import com.example.projecta.domain.dto.model.UserNotFoundException;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final TandCPService tandCPService;
    private final PeripheralPService peripheralPService;

    private final HardwarePService hardwarePService;

    private final PcPService pcPService;

    private final ProductService productService;
    private final ModelMapper modelMapper;

    private List<ShoppingCartModel> shoppingCartModelList = new ArrayList<>();

    private final UserRepository userRepository;

    @Autowired
    public ProductsController(TandCPService tandCPService, PeripheralPService peripheralPService, HardwarePService hardwarePService, PcPService pcPService, ProductService productService, ModelMapper modelMapper, UserRepository userRepository) {
        this.tandCPService = tandCPService;
        this.peripheralPService = peripheralPService;
        this.hardwarePService = hardwarePService;
        this.pcPService = pcPService;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String add() {
        return "addButtons";
    }


    @ModelAttribute
    public TandCbindingModel tandCBindingModel() {
        return new TandCbindingModel();
    }

    @GetMapping("/addTC")
    public String addTandC() {
        return "add-TableAndChair";
    }

    @PostMapping("/addTC")
    public String addTandCC(@Valid TandCbindingModel tandCBindingModel, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("tandCBindingModel", tandCBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tandCBindingModel", bindingResult);

            return "redirect:addTC";
        }


        tandCPService.addTC(tandCBindingModel);


        return "redirect:/";
    }

    @ModelAttribute
    public PeripheralBindingModel peripheralBindingModel() {
        return new PeripheralBindingModel();
    }

    @GetMapping("/addPE")
    public String addPeripheral() {
        return "add-peripheral";
    }

    @PostMapping("/addPE")
    public String addPeripheralC(@Valid PeripheralBindingModel peripheralBindingModel, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("peripheralBindingModel", peripheralBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.peripheralBindingModel", bindingResult);

            return "redirect:addPE";
        }


        peripheralPService.addPE(peripheralBindingModel);


        return "redirect:/";
    }

    @ModelAttribute
    public HardwareBindingModel hardwareBindingModel() {
        return new HardwareBindingModel();
    }

    @GetMapping("/addHC")
    public String addHardware() {
        return "add-hardware";
    }

    @PostMapping("/addHC")
    public String addHardwareC(@Valid HardwareBindingModel hardwareBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hardwareBindingModel", hardwareBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.hardwareBindingModel", bindingResult);

            return "redirect:addHC";
        }


        hardwarePService.addHC(hardwareBindingModel);


        return "redirect:/";
    }

    @ModelAttribute
    public PcBindingModel pcbindingModel() {
        return new PcBindingModel();
    }

    @GetMapping("/addPC")
    public String addPC() {
        return "add-pc";
    }

    @PostMapping("/addPC")
    public String addConfirm(@Valid PcBindingModel pcbindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("pcbindingModel", pcbindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.pcbindingModel", bindingResult);

            return "redirect:addPC";
        }

        pcPService.addPC(pcbindingModel);

        return "redirect:/";
    }

    @GetMapping("/view")
    public String viewSC(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        model.addAttribute("cartLISTTHC", user.getHardware());
        model.addAttribute("cartLISTTPE", user.getPeripherals());
        model.addAttribute("cartLISTTPC", user.getPcs());
        model.addAttribute("cartLISTTTC", user.gettANDcs());

        Double sumHC = hardwarePService.getSumOfAllElements(user.getHardware());
        Double sumPE = peripheralPService.getSumOfAllElements(user.getPeripherals());
        Double sumPC = pcPService.getSumOfAllElements(user.getPcs());
        Double sumTC = tandCPService.getSumOfAllElements(user.gettANDcs());

        model.addAttribute("totalSum", sumHC + sumPE + sumPC + sumTC);
        model.addAttribute("quantity", user.getHardware().size() + user.getPeripherals().size() + user.getPcs().size() + user.gettANDcs().size());


        return "shoppingCart";
    }

    @GetMapping("/buy/all")
    public String buyAllP(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        if (user == null) {
            throw new UserNotFoundException(principal.getName());
        }

        Set<HardwareP> hardwarePS = hardwarePService.fill(user.getHardware());
        Set<PeripheralP> peripheralPS = peripheralPService.fill(user.getPeripherals());
        Set<PcP> pcPS = pcPService.fill(user.getPcs());
        Set<TandCP> tandCPS = tandCPService.fill(user.gettANDcs());


        hardwarePService.buyAllHardware(principal);
        peripheralPService.buyAllPeripheral(principal);
        pcPService.buyAllPc(principal);
        tandCPService.buyAllTandC(principal);

        for (HardwareP hardwareP : hardwarePS) {
            hardwarePService.removeHcFromDataBase(hardwareP);
        }

        for (PeripheralP peripheralP : peripheralPS) {
            peripheralPService.removePeFromDataBase(peripheralP);
        }

        for (PcP pcP: pcPS) {
            pcPService.removePcFromDataBase(pcP);
        }

        for (TandCP tandCP : tandCPS) {
            tandCPService.removeTcFromDataBase(tandCP);
        }


        return "shoppingCart";
    }

    @GetMapping("/show/bought/{id}")
    public String showBought(@PathVariable("id") Long id ,Model model) {
        User user = userRepository.findById(id).orElseThrow(null);

        model.addAttribute("boughtLISTTHC", user.getHardwareBought());
        model.addAttribute("boughtLISTTPE", user.getPeripheralsBought());
        model.addAttribute("boughtLISTTPC", user.getPcsBought());
        model.addAttribute("boughtLISTTTC", user.gettANDcsBought());

        return "boughtrProducts";
    }

}
