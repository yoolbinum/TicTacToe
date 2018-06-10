package com.example.demo.service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Set<Game> getOpenGames(){
        return gameRepository.findAllByOpenIsTrue();
    }

    public Set<Game> getInGames(){
        return gameRepository.findAllByInIsTrue();
    }

    public Set<Game> getCloseGames(){
        return gameRepository.findAllByEndedIsTrue();
    }
}
