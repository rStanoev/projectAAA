package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsHC;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.model.HardwareModel;
import com.example.projecta.domain.dto.model.ProductNotFoundException;
import com.example.projecta.domain.dto.model.ScListModel;
import com.example.projecta.domain.dto.model.UserNotFoundException;
import com.example.projecta.helper.idKeaper;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.CommentsHCService;
import com.example.projecta.service.HardwarePService;
import com.example.projecta.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/hardware")
public class HardwareController {


    private final HardwarePService hardwarePService;

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CommentsHCService commentsHCService;

    private Set<HardwareP> hardwareModelList = new HashSet<>();

    private ScListModel scListModel;

    private final UserRepository userRepository;

    private idKeaper idKeaper = new idKeaper();

    @Autowired
    public HardwareController(HardwarePService hardwarePService, UserService userService, ModelMapper modelMapper, CommentsHCService commentsHCService, UserRepository userRepository) {
        this.hardwarePService = hardwarePService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.commentsHCService = commentsHCService;
        this.userRepository = userRepository;
    }

    @GetMapping("/listedHC")
    public String listedHC(Model model) {

        model.addAttribute("listHC", hardwarePService.getProducts());

        return "/ListedAllProducts/listedproductsHC";
    }

    @GetMapping("/listedCPU")
    public String listedCPU(Model model) {

        model.addAttribute("listCPU", hardwarePService.getProducts());

        return "Hardware/listCPU";
    }

    @GetMapping("/listedGPU")
    public String listedGPU(Model model) {

        model.addAttribute("listGPU", hardwarePService.getProducts());

        return "Hardware/listGPU";
    }

    @GetMapping("/listedMOTHERBOARD")
    public String listedMOTHERBOARD(Model model) {

        model.addAttribute("listMOTHERBOARD", hardwarePService.getProducts());

        return "Hardware/listMOTHERBOARD";
    }

    @GetMapping("/listedHDD")
    public String listedHDD(Model model) {

        model.addAttribute("listHDD", hardwarePService.getProducts());

        return "Hardware/listHDD";
    }

    @GetMapping("/listedSSD")
    public String listedSSD(Model model) {

        model.addAttribute("listSSD", hardwarePService.getProducts());

        return "Hardware/listSSD";
    }

    @GetMapping("/listedPOWERSUPPLY")
    public String listedPOWERSUPPLY(Model model) {

        model.addAttribute("listPOWERSUPPLY", hardwarePService.getProducts());

        return "Hardware/listPOWERSUPPLY";
    }

    @GetMapping("/listedCASE")
    public String listedCASE(Model model) {

        model.addAttribute("listCASE", hardwarePService.getProducts());

        return "Hardware/listCASE";
    }

    @GetMapping("/listedETC")
    public String listedETC(Model model) {

        model.addAttribute("listETC", hardwarePService.getProducts());

        return "Hardware/listETC";
    }

    @GetMapping("/details/{id}")
    public String getHardware(@PathVariable("id") Long id, Model model) {
        HardwareModel hardware = hardwarePService.getById(id);

        if (hardware == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("hardware", hardware);

        idKeaper.setId(id);

        Set<CommentsHC> hcSet = commentsHCService.getC(id);

        model.addAttribute("hcset", hcSet);

        model.addAttribute("idKeaper", idKeaper);

        return "/Hardware/detailsHC";
    }

    @GetMapping("/shopping/{id}")
    public String getP(@PathVariable("id") Long id , Model model, Principal principal) {
        HardwareP hardwareP = hardwarePService.getById2(id, principal);

        if (hardwareP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        if (user == null) {
            throw new  UserNotFoundException(principal.getName());
        }

        model.addAttribute("cartLISTTHC", user.getHardware());

        return "redirect:/";

    }

    @GetMapping("/remove/{id}")
    public String removeP(@PathVariable("id") Long id , Model model, Principal principal) {
        HardwareP hardwareP = hardwarePService.getById3(id);

        if (hardwareP == null) {
            throw new ProductNotFoundException(id);
        }

        String username = principal.getName();
        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        hardwareModelList = user.getHardware();

        this.userService.removeHC(hardwareP, user, hardwareModelList);

        hardwareModelList.clear();

        return "shoppingCart";

    }

    @GetMapping("/buy/{id}")
    public String buyP(@PathVariable("id") Long id , Model model, Principal principal) {
        String username = principal.getName();
        HardwareP hardwareP = hardwarePService.getById3(id);

        if (hardwareP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userService.getUser(username);

        if (user == null) {
            throw new  UserNotFoundException(username);
        }



        hardwareModelList = user.getHardware();

        this.userService.buyHC(hardwareP, user, hardwareModelList);

       this.hardwarePService.removeHcFromDataBase(hardwareP);

        hardwareModelList.clear();

        return "shoppingCart";

    }

    @ModelAttribute
    public CommentsBindingModel commentsBindingModel() {
        return new CommentsBindingModel();
    }

    @PostMapping("/addComment")
    public String addCommentHC(@Valid CommentsBindingModel commentsBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentsBindingModel", commentsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentsBindingModel", bindingResult);

            return "redirect:/";
        }

        Long id = idKeaper.getId();

        HardwareModel hardware = hardwarePService.getById(id);

        if (hardware == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("hardware", hardware);

        commentsHCService.addCommentHC(commentsBindingModel, id, principal);

        Set<CommentsHC> hcSet = commentsHCService.getC(id);

        model.addAttribute("hcset", hcSet);

        return "/Hardware/detailsHC";
    }


}
