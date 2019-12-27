package iastate.edu.CyHost;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import iastate.edu.Event.*;
import iastate.edu.Event.Event;
import iastate.edu.Event.EventController;
import iastate.edu.Event.EventRepository;

/**
 * @author Daniel Nikolic
 */
public class Demo5_EventMemberTest 
{
	@InjectMocks
	EventController event;

	@Mock
	EventRepository repo;

	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void eventMemberTest()
	{
		ArrayList<Event> event = new ArrayList<Event>();
		ArrayList<EventMember> member = new ArrayList<EventMember>();
		
		member.add(new EventMember());
		member.add(new EventMember());
		member.add(new EventMember());
		
		event.add(new Event()); 
		event.add(new Event()); 
		event.add(new Event()); 
		
		event.get(0).setEventName("Party 1");
		event.get(0).setId(1);
		
		event.get(1).setEventName("Party 2");
		event.get(1).setId(2);
		
		event.get(2).setEventName("Party 3");
		event.get(2).setId(3);
		
		member.get(0).setUserName("dnikolic");
		member.get(1).setUserName("hsellars");
		member.get(2).setUserName("ahmad55");
		
		member.get(0).setId(1);
		member.get(1).setId(2);
		member.get(2).setId(3);
		
		assertEquals("dnikolic", member.get(0).getUserName());
		assertEquals("hsellars", member.get(1).getUserName());
		assertEquals("ahmad55", member.get(2).getUserName());
		
		assertEquals(1, member.get(0).getEventID());
		assertEquals(2, member.get(1).getEventID());
		assertEquals(3, member.get(2).getEventID());
		
		assertEquals("Party 1", event.get(0).getEventName());
		assertEquals("Party 2", event.get(1).getEventName());
		assertEquals("Party 3", event.get(2).getEventName());
		
		
	}
	
	@Test
	public void getAllEvents() 
	{
		LinkedList<Event> everything = new LinkedList<Event>();
		everything.add(new Event("d sig", "Come to the luau", false, "ahmad55"));
		everything.add(new Event("christmas", "Don't forget a costume", true, "ahmad55"));
		everything.add(new Event("bro", "Bring a keg", true, "hsellars"));
		everything.add(new Event("Funeral", "Honor granmamie", false, "hsellars"));
		everything.add(new Event("Rakija Tasting", "Don't get to wasted", false, "dnikolic"));
		everything.add(new Event("12k", "Don't get forget water", false, "dnikolic"));
		everything.add(new Event("gaming", "for sure", true, "abdallaa"));
		everything.add(new Event("make it double", "bro", false, "abdallaa"));
		for (int i = 0; i < everything.size(); i++) {
			repo.save(everything.get(i));
		}
		when(repo.findAll()).thenReturn(everything);
		List<Event> test = repo.findAll();
		assertEquals(8, test.size());
		verify(repo, times(1)).findAll();
	}
}
