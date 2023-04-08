package com.example.projecta.scheduled;

import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.helper.Winner;
import com.example.projecta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class Game {

   private final UserService userService;

    @Autowired
    public Game(UserService userService) {
        this.userService = userService;

    }

    @Scheduled(fixedRate = 120000)
    public void restart() {
        List<User> users = userService.findAll();
        List<User> users2 = new LinkedList<>();
        Set<HardwareBought> hc = new HashSet<>();
        Set<PeripheralBought> pe = new HashSet<>();
        Set<PcBought> pc = new HashSet<>();
        Set<TandCBought> tc = new HashSet<>();
        int sum = 0;
            for (User user : users) {
                sum += user.getPoints();
                user.setPoints(0);
                user.setHardwareBought(hc);
                user.setPeripheralsBought(pe);
                user.setPcsBought(pc);
                user.settANDcsBought(tc);
                users2.add(user);
            }
        userService.sANDf2(users2, sum);
        }
    }

