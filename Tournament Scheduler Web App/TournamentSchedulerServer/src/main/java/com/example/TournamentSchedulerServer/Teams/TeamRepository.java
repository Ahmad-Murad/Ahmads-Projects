package com.example.TournamentSchedulerServer.Teams;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Teams,String>
{

    @Query(value = "SELECT name from team where tourny_id = :id and round_one = 1 ORDER BY id asc",nativeQuery  = true)
    List<String> getAllTeamsByRound1(@Param("id") int id);
    @Query(value = "SELECT name from team where tourny_id = :id and round_two = 1 ORDER BY id asc",nativeQuery  = true)
    List<String> getAllTeamsByRound2(@Param("id") int id);
    @Query(value = "SELECT name from team where tourny_id = :id and round_three = 1 ORDER BY id asc",nativeQuery  = true)
    List<String> getAllTeamsByRound3(@Param("id") int id);
    @Query(value = "SELECT name from team where tourny_id = :id and round_four = 1 ORDER BY id asc",nativeQuery  = true)
    List<String> getAllTeamsByRound4(@Param("id") int id);
    @Query(value = "select name from team where tourny_id = :id and winner = 1",nativeQuery = true)
    String getWinner(@Param("id") int id);
    @Transactional
    @Modifying
    @Query(value = "update team SET round_four = :round4,round_three = :round3,round_two = :round2,round_one = :round1, winner = :winner  where tourny_id=:id AND name = :name ",nativeQuery = true)
    void updateTeamStandings(@Param("round4") boolean round4,@Param("round3") boolean round3,@Param("round2") boolean round2,@Param("round1") boolean round1,@Param("name") String name,@Param("id") int id,
                             @Param("winner") boolean winner);
}
