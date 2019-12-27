package iastate.edu.CyHost;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import iastate.edu.Chat.WebSocketServer;
import iastate.edu.Friends.FriendRepository;

/**
 * @author Daniel Nikolic
 */
public class Demo5_FriendTest 
{
    @InjectMocks
    WebSocketServer webSocketServer;

    @Mock
    FriendRepository repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestFriendRepoGetRecentChats() 
    {
        when(repo.getExists("abc","hello")).thenReturn(0);
        when(repo.getExists("test2","test3")).thenReturn(0);
        when(repo.getExists("ok","why")).thenReturn(1);
        when(repo.getExists("as","asd")).thenReturn(0);

        assertEquals(0,repo.getExists("abc","hello"));
        assertEquals(0,repo.getExists("test2","test3"));
        assertEquals(1,repo.getExists("ok","why"));
        assertEquals(0,repo.getExists("as","asd"));

    }
}
