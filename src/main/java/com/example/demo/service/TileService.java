package com.example.demo.service;

import com.example.demo.model.Tile;
import com.example.demo.repository.GameRepository;
import com.example.demo.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TileService {
    final GameRepository gameRepository;

    final TileRepository tileRepository;

    @Autowired
    public TileService(GameRepository gameRepository, TileRepository tileRepository) {
        this.gameRepository = gameRepository;
        this.tileRepository = tileRepository;
    }

    public Tile createNewTile(int sequence){
        Tile tile = new Tile();
        tile.setSequence(sequence);
        tile.setValue("_");
        return tile;
    }

    public void saveTile(Tile tile){
        tileRepository.save(tile);
    }

    public Tile findOne(long id){
        return tileRepository.findOne(id);
    }
}
