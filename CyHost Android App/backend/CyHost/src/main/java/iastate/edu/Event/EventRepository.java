package iastate.edu.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository

public interface EventRepository extends JpaRepository<Event, String>
{
    @Query(value = "SELECT * FROM event WHERE Username = :username ORDER BY ID ASC",nativeQuery = true)
    List<Event> getAllUserEvents(@Param("username") String username);
    @Query(value = "SELECT * FROM event WHERE ID = :eid",nativeQuery = true)
    Event findByEid(@Param("eid") int eid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM event WHERE username = :username",nativeQuery = true)
    Event nukeByUserName(@Param("username") String username);
    //test


	@Modifying
	@Transactional
	@Query(value = "DELETE FROM event WHERE ID = :ID", nativeQuery = true)
	void deleteEventEntry(@Param("ID") int ID);
	
	@Query(value = "SELECT * from event WHERE Event_Name = :EventName", nativeQuery = true)
	List<Event> getAllEventsWithName(@Param("EventName") String EventName);

}
