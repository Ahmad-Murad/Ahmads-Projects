package com.example.TournamentSchedulerServer.UserControllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> 
{
	@Query("SELECT password FROM User WHERE username = :username")
	String getPassword(@Param("username") String username);
	@Query(value = "SELECT CASE WHEN EXISTS(SELECT username FROM user where((username = :username1)) THEN 1 ELSE 0 END;", nativeQuery = true)
	int getExists(@Param("username1") String username1);
}
