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

import iastate.edu.Chat.Chat;
import iastate.edu.Chat.ChatRepository;
import iastate.edu.Chat.WebSocketServer;
import iastate.edu.Event.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ChatTest {

    @InjectMocks
    WebSocketServer webSocketServer;

    @Mock
    ChatRepository repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestChatRepoGetRecentChats() {

        Chat one = new Chat(1,"ahmad55","hello");
        Chat two = new Chat(2,"hsellars","Hi");
        ArrayList<Chat> temp = new ArrayList<Chat>();
        temp.add(one);
        ArrayList<Chat> temp2 = new ArrayList<Chat>();
        temp2.add(two);
        when(repo.getRecentChats(1)).thenReturn(temp);
        when(repo.getRecentChats(2)).thenReturn(temp2);
        assertEquals("ahmad55",repo.getRecentChats(1).get(0).getUserName());
        assertEquals("hello",repo.getRecentChats(1).get(0).getMessage());
        assertEquals("hsellars",repo.getRecentChats(2).get(0).getUserName());
        assertEquals("Hi",repo.getRecentChats(2).get(0).getMessage());
    }
    @Test
    public void getAllChats()
    {
        LinkedList<Chat> everything = new LinkedList<>();
        everything.add(new Chat(1,"Come to the luau","ahmad55"));
        everything.add(new Chat(1,"Don't forget a costume","ahmad55"));
        everything.add(new Chat(1,"Bring a keg","hsellars"));


        when(repo.findAll()).thenReturn(everything);
        List<Chat> test = repo.findAll();
        assertEquals(3,test.size());
        verify(repo, times(1)).findAll();
    }

}
