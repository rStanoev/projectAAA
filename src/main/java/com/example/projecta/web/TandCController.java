package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.model.*;
import com.example.projecta.helper.idKeaper;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.CommentsTCService;
import com.example.projecta.service.TandCPService;
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
@RequestMapping("/tANDc")
public class TandCController {

    private final TandCPService tandCPService;
    private final ModelMapper modelMapper;
    private List<TCModel> tcModelList = new ArrayList<>();
    private final UserService userService;
    private ScListModel scListModel;

    private final CommentsTCService commentsTCService;
    private final UserRepository userRepository;

    private Set<TandCP> tandCPModelList = new HashSet<>();
    private idKeaper idKeaper = new idKeaper();

    @Autowired
    public TandCController(TandCPService tandCPService, ModelMapper modelMapper, UserService userService, CommentsTCService commentsTCService, UserRepository userRepository) {
        this.tandCPService = tandCPService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.commentsTCService = commentsTCService;
        this.userRepository = userRepository;
    }


    @GetMapping("/listedTC")
    public String listedTC(Model model) {

        model.addAttribute("listTC", tandCPService.getProducts());

        return "/ListedAllProducts/listedproductsTC";
    }

    @GetMapping("/listedTABLE")
    public String listedTABLE(Model model) {

        model.addAttribute("listTABLE", tandCPService.getProducts());

        return "/TableAndChair/listTABLE";
    }

    @GetMapping("/listedCHAIR")
    public String listedCHAIR(Model model) {

        model.addAttribute("listCHAIR", tandCPService.getProducts());

        return "/TableAndChair/listCHAIR";
    }

    @GetMapping("/details/{id}")
    public String getTandC(@PathVariable("id") Long id, Model model) {
        TCModel tANDc = tandCPService.getById(id);

        if (tANDc == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("tANDc", tANDc);

        idKeaper.setId(id);

        Set<CommentsTC> tcSet = commentsTCService.getC(id);

        model.addAttribute("tcset", tcSet);

        model.addAttribute("idKeaper", idKeaper);

        return "/TableAndChair/detailsTC";
    }

    @GetMapping("/shopping/{id}")
    public String getP(@PathVariable("id") Long id , Model model, Principal principal) {
        TandCP tandCP = tandCPService.getById2(id, principal);

        if (tandCP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userRepository.findByEmail(principal.getName()).orElseThrow(null);

        if (user == null) {
            throw new UserNotFoundException(principal.getName());
        }

        model.addAttribute("cartLISTTTC", user.gettANDcs());

        return "redirect:/";

    }

    @GetMapping("/remove/{id}")
    public String removeP(@PathVariable("id") Long id , Model model, Principal principal) {
        TandCP tandCP = tandCPService.getById3(id);

        if (tandCP == null) {
            throw new ProductNotFoundException(id);
        }

        String username = principal.getName();
        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        tandCPModelList = user.gettANDcs();

        this.userService.removeTC(tandCP, user, tandCPModelList);

        tandCPModelList.clear();

        return "shoppingCart";

    }

    @GetMapping("/buy/{id}")
    public String buyP(@PathVariable("id") Long id , Model model, Principal principal) {
        String username = principal.getName();
        TandCP tandCP = tandCPService.getById3(id);

        if (tandCP == null) {
            throw new ProductNotFoundException(id);
        }

        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }


        tandCPModelList = user.gettANDcs();

        this.userService.buyTC(tandCP, user, tandCPModelList);

        this.tandCPService.removeTcFromDataBase(tandCP);

        tandCPModelList.clear();

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

        TCModel tANDc = tandCPService.getById(id);

        if (tANDc == null) {
            throw new ProductNotFoundException(id);
        }

        model.addAttribute("tANDc", tANDc);

        commentsTCService.addCommentTC(commentsBindingModel, id, principal);

        Set<CommentsTC> tcSet = commentsTCService.getC(id);

        model.addAttribute("tcset", tcSet);

        return "/TableAndChair/detailsTC";
    }

}
