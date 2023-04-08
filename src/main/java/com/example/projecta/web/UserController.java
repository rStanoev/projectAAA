package com.example.projecta.web;

import com.example.projecta.domain.dto.binding.MessagesBindingModel;
import com.example.projecta.domain.dto.binding.UserRegisterBindingModel;
import com.example.projecta.domain.dto.entity.Messages;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.model.UserNotFoundException;
import com.example.projecta.domain.dto.view.UserViewModel;
import com.example.projecta.helper.Winner;
import com.example.projecta.repository.GenderRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.MessagesService;
import com.example.projecta.service.UserService;

import org.hibernate.annotations.Parameter;
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
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final GenderRepository genderRepository;
    private final UserRepository userRepository;
    private final MessagesService messagesService;



    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, GenderRepository genderRepository,
                          UserRepository userRepository, MessagesService messagesService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.genderRepository = genderRepository;
        this.userRepository = userRepository;
        this.messagesService = messagesService;
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
        boolean isExisting = userService.findUserByUserNameOrEmail(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword()) || isExisting) {
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

        if (user == null) {
            throw new UserNotFoundException(username);
        }

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

        if (user == null) {
            throw new UserNotFoundException(name);
        }

        UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);

        model.addAttribute("user", userViewModel);
        return "profile";
    }

    @GetMapping("/change/{name}")
    private String change(@PathVariable("name") String name) {
        User user = userService.getUser2(name);

        if (user == null) {
            throw new UserNotFoundException(name);
        }

        userService.changeRole(user);


        return "listThatAreUsers";
    }

    @GetMapping("/show/allUsers")
    public String showAllUsers(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        model.addAttribute("userList", userService.findAllUsersExceptLoggedOne(user));

        return "listOfAllUsers";
    }


    @GetMapping("/shomMessages/{name}")
    private String showMessages(@PathVariable("name") String name, Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        User user1 = userService.getUser2(name);


        List<Messages> m = userService.getConversation(user, user1);

        model.addAttribute("messagesList", m);
        model.addAttribute("loggedUser", user.getId());
        model.addAttribute("otherUser", user1.getId());

        return "Messages";
    }

    @ModelAttribute("messagesBindingModel")
    public MessagesBindingModel mesInit() {
        return new MessagesBindingModel();
    }

    @PostMapping("/addMessages/{id}")
    public String addMessages(@Valid MessagesBindingModel messagesBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Principal principal, @PathVariable("id") Long id, Model model) throws IOException {

        String username = principal.getName();
        User user = userService.getUser(username);
        User user1 = userService.getById3(id);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("messagesBindingModel", messagesBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messagesBindingModel", bindingResult);


            String name = user1.getUsername();

            return "redirect:/";

        }


        messagesService.addMessage(messagesBindingModel, id, user);

        List<Messages> m = userService.getConversation(user, user1);

        model.addAttribute("messagesList", m);
        model.addAttribute("loggedUser", user.getId());
        model.addAttribute("otherUser", user1.getId());

        return "Messages";
    }

    @GetMapping("/rankList")
    public String rank(Model model) {
        List<User> users = userService.findAllUsersDesc();

        model.addAttribute("usersByPoints", users);


        return "gameTable";
    }



}
