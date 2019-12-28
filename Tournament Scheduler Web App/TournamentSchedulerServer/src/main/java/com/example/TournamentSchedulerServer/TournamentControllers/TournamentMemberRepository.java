package com.example.TournamentSchedulerServer.TournamentControllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentMemberRepository extends JpaRepository<TournamentMember,String>
{
	@Query(value = "SELECT ID FROM tournament_member WHERE Username = :username",nativeQuery = true)
	List<Integer> getAllParticipatingTournys(@Param("username") String username);

	@Query(value = "SELECT username FROM event_members WHERE ID = :eid",nativeQuery = true)
	List<String> getAllInvitees(@Param("eid") int eid);
}
