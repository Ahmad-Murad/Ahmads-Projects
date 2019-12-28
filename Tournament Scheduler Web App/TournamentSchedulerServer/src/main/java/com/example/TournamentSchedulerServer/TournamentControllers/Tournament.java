package com.example.TournamentSchedulerServer.TournamentControllers;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private int id;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "description")
    @NotFound(action = NotFoundAction.IGNORE)
    private String description;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size")
    @NotFound(action = NotFoundAction.IGNORE)
    private int size;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;


    public int getSize() {
        return size;
    }

    public String getUsername() {
        return username;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
