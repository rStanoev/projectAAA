package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.UserRegisterBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.domain.dto.view.UserViewModel;
import com.example.projecta.repository.GenderRepository;
import com.example.projecta.repository.RoleRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GenderRepository genderRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final TandCBoughtService tandCBoughtService;

    private final PeripheralBoughtService peripheralBoughtService;

    private final HardwareBoughtService hardwareBoughtService;

    private final PcBoughtService pcBoughtService;

    Set<HardwareP> hardwarePS = new HashSet<>();


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, GenderRepository genderRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, TandCBoughtService tandCBoughtService, PeripheralBoughtService peripheralBoughtService, HardwareBoughtService hardwareBoughtService, PcBoughtService pcBoughtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.genderRepository = genderRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.tandCBoughtService = tandCBoughtService;
        this.peripheralBoughtService = peripheralBoughtService;
        this.hardwareBoughtService = hardwareBoughtService;
        this.pcBoughtService = pcBoughtService;
    }


    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        User user = new User(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail(),
                passwordEncoder.encode(userRegisterBindingModel.getPassword())
        );
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByName(UserRoles.USER));
        user.setRoles(roleSet);
        user.setGender(this.genderRepository.findByType(userRegisterBindingModel.getGender()));
        user.setFullName(userRegisterBindingModel.getFullName());
        user.setBorn(userRegisterBindingModel.getBorn());

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    @Override
    public User getUser2(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(name + " was not found!"));
    }

    @Override
    public void changeRole(User user) {
        Set<Role> role = new LinkedHashSet<>();
        role.add(roleRepository.findByName(UserRoles.ADMIN));
        user.setRoles(role);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserViewModel findUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        return this.modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public void removeHC(HardwareP hardwareP, User user, Set<HardwareP> hardwareModelList) {
        Set<HardwareP> hardwarePS1 = new LinkedHashSet<>();

        for (HardwareP hardwareP1 : hardwareModelList) {
            if (!Objects.equals(hardwareP1.getId(), hardwareP.getId())) {
                hardwarePS1.add(hardwareP1);
            }
        }

        user.setHardware(hardwarePS1);

         userRepository.saveAndFlush(user);
    }

    @Override
    public void removePE(PeripheralP peripheralP, User user, Set<PeripheralP> peripheralModelList) {
        Set<PeripheralP> peripheralPS = new LinkedHashSet<>();

        for (PeripheralP peripheralP1 : peripheralModelList) {
            if (!Objects.equals(peripheralP1.getId(), peripheralP.getId())) {
                peripheralPS.add(peripheralP1);
            }
        }

        user.setPeripherals(peripheralPS);

        userRepository.saveAndFlush(user);
    }

    @Override
    public void removePC(PcP pcP, User user, Set<PcP> pcPModelList) {
        Set<PcP> pcPS = new LinkedHashSet<>();

        for (PcP pcP1 : pcPModelList) {
            if (!Objects.equals(pcP1.getId(), pcP.getId())) {
                pcPS.add(pcP1);
            }
        }

        user.setPcs(pcPS);

        userRepository.saveAndFlush(user);
    }

    @Override
    public void removeTC(TandCP tandCP, User user, Set<TandCP> tandcPModelList) {
        Set<TandCP> tandCPS = new LinkedHashSet<>();

        for (TandCP tandCP1 : tandcPModelList) {
            if (!Objects.equals(tandCP1.getId(), tandCP.getId())) {
                tandCPS.add(tandCP1);
            }
        }

        user.settANDcs(tandCPS);

        userRepository.saveAndFlush(user);
    }

    @Override
    public void buyHC(HardwareP hardwareP, User user, Set<HardwareP> hardwareModelList) {
        Set<HardwareBought> hardwarePS2 = user.getHardwareBought();
           Set<HardwareP> hardwarePS1 = new LinkedHashSet<>();

        for (HardwareP hardwareP1 : hardwareModelList) {
            if (Objects.equals(hardwareP1.getId(), hardwareP.getId())) {
                HardwareBought hardwareBought = new HardwareBought(
                        hardwareP1.getName(),
                        hardwareP1.getPrice(),
                        hardwareP1.getHardware()
                );
                LocalDateTime localDateTime = LocalDateTime.now();
                hardwareBought.setTime(localDateTime);
                hardwareBoughtService.add(hardwareBought);
                hardwarePS2.add(hardwareBought);
            }
               else {
                  hardwarePS1.add(hardwareP1);
               }
        }

        user.setHardwareBought(hardwarePS2);
          user.setHardware(hardwarePS1);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void buyPE(PeripheralP peripheralP, User user, Set<PeripheralP> peripheralModelList) {
        Set<PeripheralBought> peripheralPSS = user.getPeripheralsBought();
        Set<PeripheralP> peripheralPS = new LinkedHashSet<>();

        for (PeripheralP peripheralP1 : peripheralModelList) {
            if (Objects.equals(peripheralP1.getId(), peripheralP.getId())) {
                PeripheralBought peripheralBought = new PeripheralBought(
                        peripheralP1.getName(),
                        peripheralP1.getPrice(),
                        peripheralP1.getPeripheral()
                );
                peripheralBought.setTime(LocalDateTime.now());
                peripheralBoughtService.add(peripheralBought);
                peripheralPSS.add(peripheralBought);
            }
             else {
                peripheralPS.add(peripheralP1);
            }
        }

        user.setPeripheralsBought(peripheralPSS);
        user.setPeripherals(peripheralPS);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void buyPC(PcP pcP, User user, Set<PcP> pcPModelList) {
        Set<PcBought> pcPSS = user.getPcsBought();
           Set<PcP> pcPS = new LinkedHashSet<>();

        for (PcP pcP1 : pcPModelList) {
            if (Objects.equals(pcP1.getId(), pcP.getId())) {
                PcBought pcBought = new PcBought(
                        pcP1.getName(),
                        pcP1.getPrice(),
                        pcP1.getPc()
                );
                pcBought.setTime(LocalDateTime.now());
                pcBoughtService.add(pcBought);
                pcPSS.add(pcBought);
            }
             else {
                  pcPS.add(pcP1);
              }
        }

        user.setPcsBought(pcPSS);
           user.setPcs(pcPS);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void buyTC(TandCP tandCP, User user, Set<TandCP> tandcPModelList) {
        Set<TandCBought> tandCPSS = user.gettANDcsBought();
            Set<TandCP> tandCPS = new LinkedHashSet<>();

        for (TandCP tandCP1 : tandcPModelList) {
            if (Objects.equals(tandCP1.getId(), tandCP.getId())) {
                TandCBought tandCBought = new TandCBought(
                  tandCP1.getName(),
                  tandCP1.getPrice(),
                  tandCP1.getTandC()
                );
                tandCBought.setTime(LocalDateTime.now());
                tandCBoughtService.add(tandCBought);
                tandCPSS.add(tandCBought);
            }
              else {
                 tandCPS.add(tandCP1);
                }
        }

        user.settANDcsBought(tandCPSS);
       user.settANDcs(tandCPS);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllWithUsersRole() {
        List<User> users = this.userRepository.findAll();
        List<User> users1 = new LinkedList<>();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Set<Role> role = user.getRoles();
            for (Role role1 : role) {
                if (role1.getName().equals(UserRoles.USER)) {
                    users1.add(user);
                }
            }
        }

        return users1;
    }




}
