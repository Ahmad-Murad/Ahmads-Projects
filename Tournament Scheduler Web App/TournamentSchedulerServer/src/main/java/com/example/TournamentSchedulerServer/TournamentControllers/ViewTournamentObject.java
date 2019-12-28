package com.example.TournamentSchedulerServer.TournamentControllers;

public class ViewTournamentObject {

    private String name;
    private int id;
    public ViewTournamentObject(String name,int id)
    {
        setName(name);
        setId(id);
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
