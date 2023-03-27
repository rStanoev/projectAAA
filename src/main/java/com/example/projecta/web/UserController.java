package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.UserRegisterBindingModel;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.view.UserViewModel;
import com.example.projecta.repository.GenderRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final GenderRepository genderRepository;
    private final UserRepository userRepository;


    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, GenderRepository genderRepository,
                          UserRepository userRepository) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.genderRepository = genderRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel regInit() {
        return new UserRegisterBindingModel();
    }



    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }


       this.userService.registerUser(userRegisterBindingModel);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    private String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getUser(username);

        UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);

        model.addAttribute("user", userViewModel);
        return "profile";
    }

    @GetMapping("/all")
    private String allUsers(Model model) {

        List<User> userSet = userService.getAllWithUsersRole();

        model.addAttribute("usersSet", userSet);
        return "listThatAreUsers";
    }

    @GetMapping("/profile2/{name}")
    private String profile2(@PathVariable("name") String name, Model model) {
        User user = userService.getUser2(name);

        UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);

        model.addAttribute("user", userViewModel);
        return "profile";
    }

    @GetMapping("/change/{name}")
    private String change(@PathVariable("name") String name) {
        User user = userService.getUser2(name);
        userService.changeRole(user);


        return "listThatAreUsers";
    }


}
