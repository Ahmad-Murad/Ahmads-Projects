package iastate.edu.Friends;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend,String> {
    @Query(value = "SELECT CASE WHEN EXISTS(SELECT username FROM friend_list where((username = :username1 AND friend = :username2) OR (username = :username2 AND friend = :username1))) THEN 1 ELSE 0 END;", nativeQuery = true)
    int getExists(@Param("username1") String username1,@Param("username2") String username2);
    @Query(value = "SELECT username From friend_list where friend = :username UNION SELECT friend FROM friend_list where username = :username", nativeQuery = true)
    List<String> getFriendsByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM friend_list where((username = :username1 AND friend = :username2) OR (username = :username2 AND friend = :username1)) ;", nativeQuery = true)
    void deleteRow(@Param("username1") String username1,@Param("username2") String username2);
}
