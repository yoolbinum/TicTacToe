package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Game;
import com.example.demo.model.Tile;
import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.TileService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private TileService tileService;

    private final String gameDir = "model/game/";

    @GetMapping("/open")
    public String openGames(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        Set<Game> games = gameService.getOpenGames(user);
        model.addAttribute("games", games);
        return  gameDir + "openList";
    }

    @GetMapping("/in")
    public String inGames(Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        model.addAttribute("games", gameService.getInGames(user));

        return  gameDir + "inList";
    }

    @PostMapping("/create")
    public String createGame(Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        Game game = gameService.createNewGame(user);
        game.addPlayer(user);
        user.addGame(game);
        gameService.saveGame(game);
        userService.saveUser(user);

        return "redirect:/games/in";
    }

    @GetMapping("/join/{id}")
    public String joinGame(@PathVariable("id") long id, Model model, Authentication auth){
        Game game = gameService.findOne(id);
        AppUser user = userService.findByUsername(auth.getName());
        if(game.getPlayers().contains(user)){
            return "error";
        }
        game.addPlayer(user);
        user.addGame(game);

        gameService.saveGame(game);
        userService.saveUser(user);

        return "redirect:/games/in";
    }

    @GetMapping("/play/{id}")
    public String playGame(@PathVariable("id") long id, Model model, Authentication auth){
        Game game = gameService.findOne(id);
        AppUser user = userService.findByUsername(auth.getName());
        List<Tile> board = game.getBoard();
        Map<String, Tile> tileMap = new LinkedHashMap<>();

        for(int i = 1; i<= 9; i++){
            String variableName = "tile" + i;
            tileMap.put(variableName, board.get(i - 1));
        }
        model.addAllAttributes(tileMap);

        return gameDir + "play";
    }

    @GetMapping("/updateTile/{id}")
    public String updateTile(@PathVariable("id") long id, Model model, Authentication auth){
        AppUser user = userService.findByUsername(auth.getName());
        Game currentGame = (Game)gameService.getInGames(user).toArray()[0];
        Tile tile = tileService.findOne(id);

        if(user.getUsername().equalsIgnoreCase(currentGame.getHostUsername())){
            tile.setValue("O");
        }else{
            tile.setValue("X");
        }
        tileService.saveTile(tile);
        return "redirect:/games/play/" + currentGame.getId();
    }

}
