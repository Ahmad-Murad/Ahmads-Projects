package iastate.edu.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> 
{
	@Query(value = "select * from event_chat where ID = :id ORDER BY Time ASC LIMIT 10;", nativeQuery = true)
	List<Chat> getRecentChats(@Param("id") int eventID);
}
