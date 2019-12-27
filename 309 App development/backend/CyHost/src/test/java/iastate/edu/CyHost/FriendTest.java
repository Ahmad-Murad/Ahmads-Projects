package iastate.edu.CyHost;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import iastate.edu.Chat.Chat;
import iastate.edu.Chat.ChatRepository;
import iastate.edu.Chat.WebSocketServer;
import iastate.edu.Event.*;
import iastate.edu.Friends.Friend;
import iastate.edu.Friends.FriendRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class FriendTest {

    @InjectMocks
    WebSocketServer webSocketServer;

    @Mock
    FriendRepository repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestFriendRepoGetRecentChats() {
        when(repo.getExists("ahmad55","abdallaa")).thenReturn(1);
        when(repo.getExists("hsellars","dnikolic")).thenReturn(0);
        when(repo.getExists("dnikolic","abdallaa")).thenReturn(1);
        when(repo.getExists("hsellars","abdallaa")).thenReturn(1);

        assertEquals(1,repo.getExists("ahmad55","abdallaa"));
        assertEquals(0,repo.getExists("hsellars","dnikolic"));
        assertEquals(1,repo.getExists("dnikolic","abdallaa"));
        assertEquals(1,repo.getExists("hsellars","abdallaa"));

    }
}