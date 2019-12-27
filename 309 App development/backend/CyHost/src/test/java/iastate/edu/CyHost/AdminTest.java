package iastate.edu.CyHost;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import iastate.edu.User.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This is a test to make sure the administrator use case is
 * working as expected.
 * @author Daniel Nikolic
 */
public class AdminTest 
{
	@InjectMocks
	UserController user;

	@Mock
	UserRepository repo;
	
	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Checks to see if whether a user is an administrator or not.
	 */
	@Test
	public void isAdmin()
	{
		user.saveAccount("dnikolic", "coms309", "Daniel", "Nikolic", "dnikolic@iastate.edu", 60134);
		assertEquals(false, user.isAdmin("dnikolic").isAdmin());
		
		User abdallaa = new User();
		abdallaa.setUserName("abdallaa");
		abdallaa.setPassword("coms309");
		abdallaa.setFirstName("Abdalla");
		abdallaa.setLastName("Abdelrahman");
		abdallaa.setEmail("abdallaa@iastate.edu");
		abdallaa.setZipCode(60134);
		abdallaa.setAdmin(true);
		
		assertEquals(true, abdallaa.isAdmin());
	}
	
	/**
	 * This method will test the functionality of deleting a user from the database.
	 * This is one of the benefits of being an administrator.
	 */
	@Test
	public void deleteUser()
	{	
		user.saveAccount("dnikolic", "coms309", "Daniel", "Nikolic", "dnikolic@iastate.edu", 60134);
		
		user.deleteUser("dnikolic");
		
		List<User> temp = new ArrayList(); // empty
		
		assertEquals(temp, user.getAllUsers());
	}
}
