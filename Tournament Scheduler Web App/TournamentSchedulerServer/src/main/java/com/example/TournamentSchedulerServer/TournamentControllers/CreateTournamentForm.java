package com.example.TournamentSchedulerServer.TournamentControllers;

import com.example.TournamentSchedulerServer.Teams.Teams;

public class CreateTournamentForm {
    private Tournament tournament;
    private Teams[] teams;
    private String[] invitees;

    public Teams[] getTeams() {
        return teams;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTeam(Teams[] team) {
        this.teams = team;
    }

    public void setTeams(Teams[] teams) {
        this.teams = teams;
    }

    public String[] getInvitees() {
        return invitees;
    }

    public void setInvitees(String[] invitees) {
        this.invitees = invitees;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
