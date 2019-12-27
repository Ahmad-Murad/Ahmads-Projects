package iastate.edu.CyHost;
/**
 * @author Ahmad Alramahi
 */

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


public class EventTests {

    @InjectMocks
    EventController eventService;

    @Mock
    EventRepository repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEventsByUsernameTest()
    {
        ArrayList<Event> ahmadTest = new ArrayList<Event>();
        ArrayList<Event> haydenTest = new ArrayList<Event>();
        ArrayList<Event> danielTest = new ArrayList<Event>();
        ArrayList<Event> abdallaTest = new ArrayList<Event>();
        ahmadTest.add(new Event("Aloha party","Come to the luau",false,"ahmad55"));
        ahmadTest.add(new Event("Halloween party","Don't forget a costume",true,"ahmad55"));
        haydenTest.add(new Event("Frat Party","Bring a keg",true,"hsellars"));
        haydenTest.add(new Event("Funeral","Honor granmamie",false,"hsellars"));
        danielTest.add(new Event("Rakija Tasting","Don't get to wasted",false,"dnikolic"));
        danielTest.add(new Event("5k","Don't get forget water",false,"dnikolic"));
        abdallaTest.add(new Event("Frat Formal","Bring a Date",true,"abdallaa"));
        abdallaTest.add(new Event("Chill day","Don't bring trouble",false,"abdallaa"));
// done
        when(repo.getAllUserEvents("ahmad55")).thenReturn(ahmadTest);
        when(repo.getAllUserEvents("hsellars")).thenReturn(haydenTest);
        when(repo.getAllUserEvents("dnikolic")).thenReturn(danielTest);
        when(repo.getAllUserEvents("abdallaa")).thenReturn(abdallaTest);




        assertEquals("Halloween party",ahmadTest.get(1).getEventName());
        assertEquals("Don't forget a costume",ahmadTest.get(1).getEventDescription());
        assertEquals(true,ahmadTest.get(1).getPrivacy());
        assertEquals("ahmad55",ahmadTest.get(1).getUsername());

        assertEquals("Aloha party",ahmadTest.get(0).getEventName());
        assertEquals("Come to the luau",ahmadTest.get(0).getEventDescription());
        assertEquals(false,ahmadTest.get(0).getPrivacy());
        assertEquals("ahmad55",ahmadTest.get(0).getUsername());

        assertEquals("Frat Party",haydenTest.get(0).getEventName());
        assertEquals("Bring a keg",haydenTest.get(0).getEventDescription());
        assertEquals(true,haydenTest.get(0).getPrivacy());
        assertEquals("hsellars",haydenTest.get(0).getUsername());

        assertEquals("Funeral",haydenTest.get(1).getEventName());
        assertEquals("Honor granmamie",haydenTest.get(1).getEventDescription());
        assertEquals(false,haydenTest.get(1).getPrivacy());
        assertEquals("hsellars",haydenTest.get(1).getUsername());

        assertEquals("Rakija Tasting",danielTest.get(0).getEventName());
        assertEquals("Don't get to wasted",danielTest.get(0).getEventDescription());
        assertEquals(false,danielTest.get(0).getPrivacy());
        assertEquals("dnikolic",danielTest.get(0).getUsername());

        assertEquals("5k",danielTest.get(1).getEventName());
        assertEquals("Don't get forget water",danielTest.get(1).getEventDescription());
        assertEquals(false,danielTest.get(1).getPrivacy());
        assertEquals("dnikolic",danielTest.get(1).getUsername());

        assertEquals("Frat Formal",abdallaTest.get(0).getEventName());
        assertEquals("Bring a Date",abdallaTest.get(0).getEventDescription());
        assertEquals(true,abdallaTest.get(0).getPrivacy());
        assertEquals("abdallaa",abdallaTest.get(0).getUsername());

        assertEquals("Chill day",abdallaTest.get(1).getEventName());
        assertEquals("Don't bring trouble",abdallaTest.get(1).getEventDescription());
        assertEquals(false,abdallaTest.get(1).getPrivacy());
        assertEquals("abdallaa",abdallaTest.get(1).getUsername());
    }
    @Test
    public void getAllEvents()
    {
       LinkedList<Event> everything = new LinkedList<Event>();
        everything.add(new Event("Aloha party","Come to the luau",false,"ahmad55"));
        everything.add(new Event("Halloween party","Don't forget a costume",true,"ahmad55"));
        everything.add(new Event("Frat Party","Bring a keg",true,"hsellars"));
        everything.add(new Event("Funeral","Honor granmamie",false,"hsellars"));
        everything.add(new Event("Rakija Tasting","Don't get to wasted",false,"dnikolic"));
        everything.add(new Event("5k","Don't get forget water",false,"dnikolic"));
        everything.add(new Event("Frat Formal","Bring a Date",true,"abdallaa"));
        everything.add(new Event("Chill day","Don't bring trouble",false,"abdallaa"));
        for (int i =0; i < everything.size();i++)
        {
            repo.save(everything.get(i));
        }
        when(repo.findAll()).thenReturn(everything);
        List<Event> test = repo.findAll();
        assertEquals(8,test.size());
        verify(repo, times(1)).findAll();
    }


}

