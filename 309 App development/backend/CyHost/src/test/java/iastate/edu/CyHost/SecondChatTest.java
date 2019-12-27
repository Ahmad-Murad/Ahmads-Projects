package iastate.edu.CyHost;
/**
 * @author Ahmad Alramahi
 */
import iastate.edu.Chat.Chat;
import iastate.edu.Chat.ChatRepository;
import iastate.edu.Chat.WebSocketServer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SecondChatTest {
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

        Chat one = new Chat(1,"abdallaa","hello");
        Chat two = new Chat(2,"dnikolic","Hi");
        ArrayList<Chat> temp = new ArrayList<Chat>();
        temp.add(one);
        ArrayList<Chat> temp2 = new ArrayList<Chat>();
        temp2.add(two);
        when(repo.getRecentChats(1)).thenReturn(temp);
        when(repo.getRecentChats(2)).thenReturn(temp2);
        assertEquals("abdallaa",repo.getRecentChats(1).get(0).getUserName());
        assertEquals("hello",repo.getRecentChats(1).get(0).getMessage());
        assertEquals("dnikolic",repo.getRecentChats(2).get(0).getUserName());
        assertEquals("Hi",repo.getRecentChats(2).get(0).getMessage());
    }
    @Test
    public void getAllChats()
    {
        LinkedList<Chat> everything = new LinkedList<>();
        everything.add(new Chat(1,"Come to the luau","ahmad55"));
        everything.add(new Chat(1,"Don't forget a costume","ahmad55"));
        everything.add(new Chat(1,"Bring a keg","hsellars"));
        everything.add(new Chat(1,"get some rakija","dnikolic"));

        when(repo.findAll()).thenReturn(everything);
        List<Chat> test = repo.findAll();
        assertEquals(4,test.size());
        verify(repo, times(1)).findAll();
    }
}
