package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.model.PcModel;
import com.example.projecta.domain.dto.model.ProductNotFoundException;
import com.example.projecta.domain.dto.model.ScListModel;
import com.example.projecta.domain.dto.model.UserNotFoundException;
import com.example.projecta.helper.idKeaper;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.CommentsPCService;
import com.example.projecta.service.PcPService;
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
@RequestMapping("/pc")
public class PcController {

    private final PcPService pcPService;

    private final UserService userService;
    private final ModelMapper modelMapper;

    private List<PcModel> pcModelList = new ArrayList<>();
    private ScListModel scListModel;
    private final UserRepository userRepository;

    private final CommentsPCService commentsPCService;

    private Set<PcP> pcPModelList = new HashSet<>();

    private idKeaper idKeaper = new idKeaper();

    @Autowired
    public PcController(PcPService pcPService, UserService userService, ModelMapper modelMapper,
                        UserRepository userRepository, CommentsPCService commentsPCService) {
        this.pcPService = pcPService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.commentsPCService = commentsPCService;
    }

    @GetMapping("/listedPC")
    public String listedPC(Model model) {

        model.addAttribute("listPC", pcPService.getProducts());

        return "/ListedAllProducts/listedproductsPC";
    }

    @GetMapping("/listedPCs")
    public String listedPCs(Model model) {

        model.addAttribute("listPCs", pcPService.getProducts());

        return "/Pc/listPC";
    }

    @GetMapping("/listedLAPTOP")
    public String listedLAPTOP(Model model) {

        model.addAttribute("listLAPTOP", pcPService.getProducts());

        return "/Pc/listLAPTOP";
    }

    @GetMapping("/listedCONSOLE")
    public String listedCONSOLE(Model model) {

        model.addAttribute("listCONSOLE", pcPService.getProducts());

        return "/Pc/listCONSOLE";
    }

    @GetMapping("/details/{id}")
    public String getPc(@PathVariable("id") Long id, Model model) {
        PcModel pc = pcPService.getById(id);

        if (pc == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("pc", pc);

        idKeaper.setId(id);

        Set<CommentsPC> pcSet = commentsPCService.getC(id);

        model.addAttribute("pcset", pcSet);

        model.addAttribute("idKeaper", idKeaper);

        return "/Pc/detailsPC";
    }

    @GetMapping("/shopping/{id}")
    public String getP(@PathVariable("id") Long id , Model model, Principal principal) {
        PcP pcP = pcPService.getById2(id, principal);

        if (pcP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        if (user == null) {
            throw new UserNotFoundException(principal.getName());
        }

        model.addAttribute("cartLISTTPC", user.getPcs());

        return "redirect:/";

    }

    @GetMapping("/remove/{id}")
    public String removeP(@PathVariable("id") Long id , Model model, Principal principal) {
        PcP pcP = pcPService.getById3(id);

        if (pcP == null) {
            throw new ProductNotFoundException(id);
        }

        String username = principal.getName();
        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        pcPModelList = user.getPcs();

        this.userService.removePC(pcP, user, pcPModelList);

        pcPModelList.clear();

        return "shoppingCart";

    }

    @GetMapping("/buy/{id}")
    public String buyP(@PathVariable("id") Long id , Model model, Principal principal) {
        String username = principal.getName();
        PcP pcP = pcPService.getById3(id);

        if (pcP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }



        pcPModelList = user.getPcs();

        this.userService.buyPC(pcP, user, pcPModelList);
        this.pcPService.removePcFromDataBase(pcP);

        pcPModelList.clear();

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

        PcModel pc = pcPService.getById(id);

        if (pc == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("pc", pc);

        commentsPCService.addCommentPC(commentsBindingModel, id, principal);

        Set<CommentsPC> pcSet = commentsPCService.getC(id);

        model.addAttribute("pcset", pcSet);

        return "/Pc/detailsPC";
    }


}
