package com.example.demo.repository;

import com.example.demo.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface GameRepository extends CrudRepository<Game, Long>{
    Set<Game> findAllByOpenIsTrue();
    Set<Game> findAllByInIsTrue();
    Set<Game> findAllByEndedIsTrue();
}
