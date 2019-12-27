package com.example.TournamentSchedulerServer.TournamentControllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament,String> {
    @Query(value = "SELECT Event_Name, ID,Event_Description,Event_Date,P_Status, Username FROM event WHERE Username = :username ORDER BY ID ASC",nativeQuery = true)
    List<Tournament> getAllUserEvents(@Param("username") String username);
    @Query(value = "SELECT * FROM tournament WHERE tournament_id = :eid",nativeQuery = true)
     Tournament findById(@Param("eid") int eid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM event WHERE username = :username",nativeQuery = true)
    Tournament nukeByUserName(@Param("username") String username);
//    @Query(value = "SELECT Event_Name, ID,Event_Description,Public,Event_Date FROM event WHERE ID = :eid",nativeQuery = true)


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM event WHERE ID = :ID", nativeQuery = true)
    void deleteEventEntry(@Param("ID") int ID);

    @Query(value = "SELECT * from tournament WHERE username = :username", nativeQuery = true)
    List<Tournament> getTournamentsWithUserName(@Param("username") String username);

}
