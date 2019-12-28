package iastate.edu.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMemberRepository extends JpaRepository<EventMember,String>
{
	@Query(value = "SELECT ID FROM event_members WHERE Username = :username",nativeQuery = true)
	List<Integer> getAllParticipatingEvents(@Param("username") String username);

	@Query(value = "SELECT username FROM event_members WHERE ID = :eid",nativeQuery = true)
	List<String> getAllInvitees(@Param("eid") int eid);
}
