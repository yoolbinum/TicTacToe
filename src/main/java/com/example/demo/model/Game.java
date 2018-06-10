package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "games")
    private Set<AppUser> players;

    private int[][] board = new int[3][3];

    private boolean ended;

    private boolean in;

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

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
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

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }
}
