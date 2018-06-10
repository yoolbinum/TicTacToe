package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/create")
    public String createGame(Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        Game game = gameService.createNewGame();
        game.addPlayer(user);
        user.addGame(game);
        gameService.saveGame(game);
        userService.saveUser(user);

        return "redirect:/games/open";
    }
}
