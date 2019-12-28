package iastate.edu.Friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,String> {
    @Query(value = "SELECT * from friend_request where DstUser = :DstUser", nativeQuery = true)
    List<FriendRequest> getRequestsByTo(@Param("DstUser") String thing);

    @Modifying
    @Transactional
    @Query(value = "DELETE from friend_request where (DstUser = :dst && SrcUser = :src)", nativeQuery = true)
    void deleteRequest(@Param("dst") String dstUser, @Param("src") String srcUser);
}
