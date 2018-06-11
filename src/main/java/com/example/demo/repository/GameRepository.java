package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface GameRepository extends CrudRepository<Game, Long>{
    Set<Game> findAllByOpenIsTrueAndPlayersNotContaining(AppUser user);
    Set<Game> findAllByPlayersContaining(AppUser user);
}
