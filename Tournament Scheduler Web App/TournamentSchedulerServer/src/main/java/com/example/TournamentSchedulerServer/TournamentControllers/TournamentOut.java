package com.example.TournamentSchedulerServer.TournamentControllers;

import java.util.List;

public class TournamentOut {
    String name;
    int numTeams;
    List<String> Teams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public List<String> getTeams() {
        return Teams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public void setTeams(List<String> teams) {
        Teams = teams;
    }

}
