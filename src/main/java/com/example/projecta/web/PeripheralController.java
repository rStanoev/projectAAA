package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.model.PeripheralModel;
import com.example.projecta.domain.dto.model.ProductNotFoundException;
import com.example.projecta.domain.dto.model.ScListModel;
import com.example.projecta.domain.dto.model.UserNotFoundException;
import com.example.projecta.helper.idKeaper;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.CommentsPEService;
import com.example.projecta.service.PeripheralPService;
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
import java.util.*;

@Controller
@RequestMapping("/peripheral")
public class PeripheralController {

    private final PeripheralPService peripheralPService;

    private Set<PeripheralP> peripheralPModelList = new HashSet<>();

    private final CommentsPEService commentsPEService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private List<PeripheralModel> peripheralModelList = new ArrayList<>();
    private ScListModel scListModel;
    private final UserRepository userRepository;

    private idKeaper idKeaper = new idKeaper();

    @Autowired
    public PeripheralController(PeripheralPService peripheralPService, CommentsPEService commentsPEService, UserService userService, ModelMapper modelMapper, UserRepository userRepository) {
        this.peripheralPService = peripheralPService;
        this.commentsPEService = commentsPEService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/listedPE")
    public String listedPE(Model model) {

        model.addAttribute("listPE", peripheralPService.getProducts());

        return "/ListedAllProducts/listedproductsPE";
    }

    @GetMapping("/listedKEYBOARD")
    public String listedKEYBOARD(Model model) {

        model.addAttribute("listKEYBOARD", peripheralPService.getProducts());

        return "/Peripheral/listKEYBOARD";
    }

    @GetMapping("/listedMOUSE")
    public String listedMOUSE(Model model) {

        model.addAttribute("listMOUSE", peripheralPService.getProducts());

        return "/Peripheral/listMOUSE";
    }

    @GetMapping("/listedHEADSET")
    public String listedHEADSET(Model model) {

        model.addAttribute("listHEADSET", peripheralPService.getProducts());

        return "/Peripheral/listHEADSET";
    }

    @GetMapping("/listedMONITOR")
    public String listedMONITOR(Model model) {

        model.addAttribute("listMONITOR", peripheralPService.getProducts());

        return "/Peripheral/listMONITOR";
    }

    @GetMapping("/listedSPEAKER")
    public String listedSPEAKER(Model model) {

        model.addAttribute("listSPEAKER", peripheralPService.getProducts());

        return "/Peripheral/listSPEAKER";
    }

    @GetMapping("/listedCONTROLLER")
    public String listedCONTROLLER(Model model) {

        model.addAttribute("listCONTROLLER", peripheralPService.getProducts());

        return "/Peripheral/listCONTROLLER";
    }

    @GetMapping("/listedETC")
    public String listedETC(Model model) {

        model.addAttribute("listETC", peripheralPService.getProducts());

        return "/Peripheral/listETC";
    }

    @GetMapping("/details/{id}")
    public String getPeripheral(@PathVariable("id") Long id, Model model) {
        PeripheralModel peripheral = peripheralPService.getById(id);

        if (peripheral == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("peripheral", peripheral);

        idKeaper.setId(id);

        Set<CommentsPE> peSet = commentsPEService.getC(id);

        model.addAttribute("peset", peSet);


        model.addAttribute("idKeaper", idKeaper);

        return "/Peripheral/detailsPE";
    }

    @GetMapping("/shopping/{id}")
    public String getP(@PathVariable("id") Long id , Model model, Principal principal) {
        PeripheralP peripheralP = peripheralPService.getById2(id,  principal);

        if (peripheralP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        if (user == null) {
            throw new  UserNotFoundException(principal.getName());
        }

        model.addAttribute("cartLISTTPE", user.getPeripherals());

        return "redirect:/";

    }

    @GetMapping("/remove/{id}")
    public String removeP(@PathVariable("id") Long id , Model model, Principal principal) {
        String username = principal.getName();
        PeripheralP peripheralP = peripheralPService.getById3(id);

        if (peripheralP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userService.getUser(username);

        if (user == null) {
            throw new  UserNotFoundException(username);
        }

        peripheralPModelList = user.getPeripherals();

        this.userService.removePE(peripheralP, user, peripheralPModelList);

        peripheralPModelList.clear();

        return "shoppingCart";

    }

    @GetMapping("/buy/{id}")
    public String buyP(@PathVariable("id") Long id , Model model, Principal principal) {
        PeripheralP peripheralP = peripheralPService.getById3(id);

        if (peripheralP == null) {
            throw new ProductNotFoundException(id);
        }

        String username = principal.getName();
        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        peripheralPModelList = user.getPeripherals();

        this.userService.buyPE(peripheralP, user, peripheralPModelList);

      this.peripheralPService.removePeFromDataBase(peripheralP);

        peripheralPModelList.clear();

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

        PeripheralModel peripheral = peripheralPService.getById(id);

        if (peripheral == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("peripheral", peripheral);

        commentsPEService.addCommentPE(commentsBindingModel, id, principal);

        Set<CommentsPE> peSet = commentsPEService.getC(id);

        model.addAttribute("peset", peSet);

        return "/Peripheral/detailsPE";
    }



}
