package com.example.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "games")
    private Set<AppUser> players;

    private String hostUsername;

    private String currentTurnUsername;

    private String winner;

    private String gameState;

    @OneToMany
    private List<Tile> board;

    private boolean ended;

    private boolean open;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<AppUser> getPlayers() {
        return players;
    }

    public void setPlayers(Set<AppUser> players) {
        this.players = players;
    }

    public List<Tile> getBoard() {
        return board;
    }

    public void setBoard(List<Tile> board) {
        this.board = board;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void addPlayer(AppUser player){
        this.players.add(player);
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public String getCurrentTurnUsername() {
        return currentTurnUsername;
    }

    public void setCurrentTurnUsername(String currentTurnUsername) {
        this.currentTurnUsername = currentTurnUsername;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    // Checks the tiles and decide whether the game is over.
    public String gameState(List<Tile> tiles){
        String tile1 = tiles.get(0).getValue();
        String tile2 = tiles.get(1).getValue();
        String tile3 = tiles.get(2).getValue();
        String tile4 = tiles.get(3).getValue();
        String tile5 = tiles.get(4).getValue();
        String tile6 = tiles.get(5).getValue();
        String tile7 = tiles.get(6).getValue();
        String tile8 = tiles.get(7).getValue();
        String tile9 = tiles.get(8).getValue();

        if(tile1.equalsIgnoreCase(tile2) && tile2.equalsIgnoreCase(tile3)){
            return "Winner";
        }
        return "Draw";
    }
}
