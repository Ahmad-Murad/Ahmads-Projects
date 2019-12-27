package com.example.TournamentSchedulerServer.Teams;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private int id;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourny_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private int tourny_id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_one")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean round1;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_two")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean round2;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_three")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean round3;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_four")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean round4;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "winner")
    @NotFound(action = NotFoundAction.IGNORE)
    private boolean winner;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getRound1() {
        return round1;
    }

    public boolean getRound2() {
        return round2;
    }

    public boolean getRound3() {
        return round3;
    }

    public int getTourny_id() {
        return tourny_id;
    }

    public boolean getRound4() {
        return round4;
    }

    public void setRound1(boolean round1) {
        this.round1 = round1;
    }

    public void setRound2(boolean round2) {
        this.round2 = round2;
    }

    public void setRound3(boolean round3) {
        this.round3 = round3;
    }

    public void setRound4(boolean round4) {
        this.round4 = round4;
    }

    public void setTourny_id(int tourny_id) {
        this.tourny_id = tourny_id;
    }

    public boolean isWinner() {
        return winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

