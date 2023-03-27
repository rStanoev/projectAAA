package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.UserRegisterBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.view.UserViewModel;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {


    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    Optional<User> findById(Long id);


    User getUser(String username);

    UserViewModel findUser(Long id);


    void removeHC(HardwareP hardwareP, User user, Set<HardwareP> hardwareModelList);

    void removePE(PeripheralP peripheralP, User user, Set<PeripheralP> peripheralModelList);

    void removePC(PcP pcP, User user, Set<PcP> pcPModelList);

    void removeTC(TandCP tandCP, User user, Set<TandCP> tandcPModelList);

    void buyHC(HardwareP hardwareP, User user, Set<HardwareP> hardwareModelList);

    void buyPE(PeripheralP peripheralP, User user, Set<PeripheralP> peripheralModelList);

    void buyPC(PcP pcP, User user, Set<PcP> pcPModelList);

    void buyTC(TandCP tandCP, User user, Set<TandCP> tandcPModelList);

    List<User> getAllWithUsersRole();

    User getUser2(String name);

    void changeRole(User user);
}
