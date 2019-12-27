package iastate.edu.CyHost;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import iastate.edu.User.*;


/**
 * @author Daniel Nikolic
 */
public class UserTest 
{
	@InjectMocks
	UserController user;

	@Mock
	UserRepository userRepo;

	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getPassword() 
	{
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(new User()); // dnikolic
		users.add(new User()); // abdallaa
		users.add(new User()); // hsellars
		users.add(new User()); // ahmad55
		users.add(new User()); // smitra
		
		users.get(0).setUserName("dnikolic");
		users.get(0).setFirstName("Daniel");
		users.get(0).setLastName("Nikolic");
		users.get(0).setPassword("kida");
		
		users.get(1).setUserName("abdallaa");
		users.get(1).setFirstName("Abdalla");
		users.get(1).setLastName("Abdelrahman");
		users.get(1).setPassword("okcomputer");
		
		users.get(2).setUserName("hsellars");
		users.get(2).setFirstName("Hayden");
		users.get(2).setLastName("Sellars");
		users.get(2).setPassword("inrainbows");
		
		users.get(3).setUserName("ahmad55");
		users.get(3).setFirstName("Ahmad");
		users.get(3).setLastName("Alramahi");
		users.get(3).setPassword("hailtothethief");
		
		users.get(4).setUserName("smitra");
		users.get(4).setFirstName("Simanta");
		users.get(4).setLastName("Mitra");
		users.get(4).setPassword("amnesiac");
		
		when(userRepo.getPassword("dnikolic")).thenReturn(users.get(0).getPassword());
		when(userRepo.getPassword("abdallaa")).thenReturn(users.get(1).getPassword());
		when(userRepo.getPassword("hsellars")).thenReturn(users.get(2).getPassword());
		when(userRepo.getPassword("ahmad55")).thenReturn(users.get(3).getPassword());
		when(userRepo.getPassword("smitra")).thenReturn(users.get(4).getPassword());

		assertEquals("dnikolic", users.get(0).getUserName());
		assertEquals("Daniel", users.get(0).getFirstName());
		assertEquals("Nikolic", users.get(0).getLastName());
		assertEquals("kida", users.get(0).getPassword());
		
		assertEquals("abdallaa", users.get(1).getUserName());
		assertEquals("Abdalla", users.get(1).getFirstName());
		assertEquals("Abdelrahman", users.get(1).getLastName());
		assertEquals("okcomputer", users.get(1).getPassword());

		assertEquals("hsellars", users.get(2).getUserName());
		assertEquals("Hayden", users.get(2).getFirstName());
		assertEquals("Sellars", users.get(2).getLastName());
		assertEquals("inrainbows", users.get(2).getPassword());

		assertEquals("ahmad55", users.get(3).getUserName());
		assertEquals("Ahmad", users.get(3).getFirstName());
		assertEquals("Alramahi", users.get(3).getLastName());
		assertEquals("hailtothethief", users.get(3).getPassword());

		assertEquals("smitra", users.get(4).getUserName());
		assertEquals("Simanta", users.get(4).getFirstName());
		assertEquals("Mitra", users.get(4).getLastName());
		assertEquals("amnesiac", users.get(4).getPassword());
	}
}
