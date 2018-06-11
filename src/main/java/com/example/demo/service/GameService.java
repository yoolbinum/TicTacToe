package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.model.Game;
import com.example.demo.model.Tile;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.TileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    final GameRepository gameRepository;

    final UserRepository userRepository;

    final TileService tileService;

    @Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository, TileService tileService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.tileService = tileService;
    }

    public Game createNewGame(AppUser user){
        Game game = new Game();
        List<Tile> tileList = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            Tile tile = tileService.createNewTile(i);
            tileService.saveTile(tile);
            tileList.add(tile);
        }

        game.setBoard(tileList);
        game.setEnded(false);
        game.setOpen(true);
        game.setPlayers(new HashSet<>());
        game.setHostUsername(user.getUsername());

        return game;
    }

    public Set<Game> getOpenGames(AppUser player){
        return gameRepository.findAllByOpenIsTrueAndPlayersNotContaining(player);
    }

    public Set<Game> getInGames(AppUser player){
        return gameRepository.findAllByPlayersContaining(player);
    }

    public Set<Game> getCloseGames(){
        return gameRepository.findAllByEndedIsTrue();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public Game findOne(long id){
        return gameRepository.findOne(id);
    }

    public Set<AppUser> getPlayers(Game game){
        return game.getPlayers();
    }

}
