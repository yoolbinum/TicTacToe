package com.example.demo.controller;

import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    private final String gameDir = "model/game/";

    @GetMapping("/open")
    public String openGames(Model model){
        model.addAttribute("games", gameService.getOpenGames());

        return  gameDir + "list";
    }

    @GetMapping("/in")
    public String inGames(Model model){
        model.addAttribute("games", gameService.getInGames());

        return  gameDir + "list";
    }

    @GetMapping("/end")
    public String endedGames(Model model){
        model.addAttribute("games", gameService.getCloseGames());

        return  gameDir + "list";
    }

}
