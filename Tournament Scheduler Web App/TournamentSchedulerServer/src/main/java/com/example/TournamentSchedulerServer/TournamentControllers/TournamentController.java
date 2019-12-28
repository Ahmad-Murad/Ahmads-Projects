package com.example.TournamentSchedulerServer.TournamentControllers;


import com.example.TournamentSchedulerServer.Teams.TeamRepository;
import com.example.TournamentSchedulerServer.Teams.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TournamentController {
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TournamentMemberRepository tournamentMemberRepository;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/Tournaments/new",produces = { "application/json"})
    @GeneratedValue
    public int saveTourny(@RequestBody CreateTournamentForm form)
    {
        Teams carrier = new Teams();
        TournamentMember holder = new TournamentMember();
        tournamentRepository.save(form.getTournament());
        for(int i =0; i < form.getTeams().length; i++)
        {
            carrier = form.getTeams()[i];
            carrier.setTourny_id(form.getTournament().getId());
            teamRepository.save(carrier);
        }
        for(int i =0; i < form.getInvitees().length; i++)
        {
            holder.setId(form.getTournament().getId());
            holder.setUserName(form.getInvitees()[i]);
            tournamentMemberRepository.save(holder);


        }
        TournamentMember creator = new TournamentMember();
        creator.setUserName(form.getTournament().getUsername());
        creator.setId(form.getTournament().getId());
        tournamentMemberRepository.save(creator);
        return form.getTournament().getId();

    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/Tournaments/{id}",produces = { "application/json"})
    @GeneratedValue
    public TournamentOut getTeamsByID(@PathVariable("id") int id)
    {
        TournamentOut carrier = new TournamentOut();
        Tournament tournament = tournamentRepository.findById(id);
        carrier.setName(tournament.getName());
        carrier.setNumTeams(tournament.getSize());
        List<String> out = teamRepository.getAllTeamsByRound1(id);
        out.addAll(teamRepository.getAllTeamsByRound2(id));
        out.addAll(teamRepository.getAllTeamsByRound3(id));
        out.addAll(teamRepository.getAllTeamsByRound4(id));
        if(teamRepository.getWinner(id) != null)
            out.add(teamRepository.getWinner(id));
        carrier.setTeams(out);
        return carrier;
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/Tournaments/access/{username}",produces = { "application/json"})
    @GeneratedValue
    public List<ViewTournamentObject> getAssociatedTournaments(@PathVariable("username") String username)
    {
        List<Integer> Tids =  tournamentMemberRepository.getAllParticipatingTournys(username);
        ArrayList<ViewTournamentObject> out = new ArrayList<ViewTournamentObject>();
        Tournament temp;
        for(int i = 0; i<Tids.size(); i++)
        {
            temp = tournamentRepository.findById(Tids.get(i));
            out.add(new ViewTournamentObject(temp.getName(),temp.getId()));
        }
        return out;
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/Tournaments/update",produces = { "application/json"})
    @GeneratedValue
    public boolean updateRoundRankings(@RequestBody Teams[] teamArray)
    {
        try {
            for(int i =0; i <teamArray.length;i++)
            {
                teamRepository.updateTeamStandings(
                        teamArray[i].getRound4(),teamArray[i].getRound3(),teamArray[i].getRound2(),teamArray[i].getRound1(),teamArray[i].getName(),teamArray[i].getTourny_id(),
                        teamArray[i].isWinner());
            }
            return true;
        }
        catch (Exception e)
        {

            throw(e);

        }
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/Tournaments/manage/{username}",produces = { "application/json"})
    @GeneratedValue
    public List<ViewTournamentObject> getManagedTournaments(@PathVariable("username") String username)
    {
        List<Tournament> temp = tournamentRepository.getTournamentsWithUserName(username);
        ArrayList<ViewTournamentObject> out = new ArrayList<ViewTournamentObject>();
        for(int i= 0; i < temp.size(); i++)
        {
            out.add(new ViewTournamentObject(temp.get(i).getName(),temp.get(i).getId()));
        }
        return out;
    }
}
