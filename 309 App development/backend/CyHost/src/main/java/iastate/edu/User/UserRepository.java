package iastate.edu.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User repository for seeking data within database
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> 
{
	@Query("SELECT password FROM User WHERE username = :username")
	String getPassword(@Param("username") String username);

	@Query(value = "SELECT * FROM user where username = :username", nativeQuery = true)
	User getUserByUsername(@Param("username") String username);

	@Query(value = "SELECT Admin FROM user where username = :username", nativeQuery = true)
	boolean getAdminStatus(@Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM user WHERE Username = :Username", nativeQuery = true)
	void deleteUserEntry(@Param("Username") String Username);
}
