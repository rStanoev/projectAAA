package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsPC;
import com.example.projecta.domain.dto.entity.CommentsPE;
import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.model.PcModel;
import com.example.projecta.domain.dto.model.ScListModel;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        model.addAttribute("cartLISTTPC", user.getPcs());

        return "shoppingCart";

    }

    @GetMapping("/remove/{id}")
    public String removeP(@PathVariable("id") Long id , Model model, Principal principal) {
        PcP pcP = pcPService.getById3(id);
        String username = principal.getName();
        User user = userService.getUser(username);
        pcPModelList = user.getPcs();

        this.userService.removePC(pcP, user, pcPModelList);

        pcPModelList.clear();

        return "shoppingCart";

    }

    @GetMapping("/buy/{id}")
    public String buyP(@PathVariable("id") Long id , Model model, Principal principal) {
        PcP pcP = pcPService.getById3(id);
        String username = principal.getName();
        User user = userService.getUser(username);
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
                               RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentsBindingModel", commentsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentsBindingModel", bindingResult);

            return "/Pc/detailsPC";
        }
        Long id = idKeaper.getId();
        commentsPCService.addCommentPC(commentsBindingModel, id, principal);

        return "/Pc/detailsPC";
    }

}
