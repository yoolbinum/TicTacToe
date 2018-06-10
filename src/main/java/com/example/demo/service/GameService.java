package com.example.demo.service;

import com.example.demo.model.AppUser;
import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class GameService {
    final GameRepository gameRepository;

    final UserRepository userRepository;

    @Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public Game createNewGame(){
        Game game = new Game();

        int[][] initializedBoard = new int[3][3];
        for(int[] row: initializedBoard){
            Arrays.fill(row, -1);
        }
        game.setBoard(initializedBoard);
        game.setEnded(false);
        game.setIn(false);
        game.setOpen(true);
        game.setPlayers(new HashSet<>());

        return game;
    }

    public Set<Game> getOpenGames(){
        return gameRepository.findAllByOpenIsTrue();
    }

    public Set<Game> getInGames(){
        return gameRepository.findAllByInIsTrue();
    }

    public Set<Game> getCloseGames(){
        return gameRepository.findAllByEndedIsTrue();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

}
