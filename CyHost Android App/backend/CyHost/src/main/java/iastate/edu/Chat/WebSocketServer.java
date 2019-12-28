package iastate.edu.Chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import iastate.edu.Event.EventMemberRepository;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{eventID}/{username}", configurator = CustomConfigurator.class)
@Component
public class WebSocketServer 
{
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	private Session session;
	private static Set<WebSocketServer> chatEndpoints = new CopyOnWriteArraySet<>();
	private static Map<String, Session> sessionMap = new HashMap<>();
	private static Map<String, String> users = new HashMap<>();
	private static Map<String, Integer> userToEventID = new HashMap<>();

	@Autowired
	EventMemberRepository eventMemberRepository;

	@Autowired
	ChatRepository chatRepository;

	@OnOpen
	public void onOpen(Session session, @PathParam("eventID") int eventID, @PathParam("username") String username) throws IOException 
	{
		logger.debug("we opened");
		this.session = session;
		chatEndpoints.add(this);
		users.put(session.getId(), username);
		sessionMap.put(session.getId(), session);
		userToEventID.put(username, eventID);
		ArrayList<Chat> chatList = (ArrayList<Chat>) chatRepository.getRecentChats(eventID);
		for (int i = 0; i < chatList.size(); i++) 
		{
			sendAMessage(session, chatList.get(i).getUserName() + ": " + chatList.get(i).getMessage());
		}
		broadcastToEvent(eventID, username + " has joined the chat");
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException 
	{
		String username = users.get(session.getId());
		int eid = userToEventID.get(username);
		Chat chat = new Chat();
		chat.setId(eid);
		chat.setMessage(message);
		chat.setUserName(username);
		chat.setTimestamp(new Timestamp(System.currentTimeMillis()));
		chatRepository.save(chat);
		broadcastToEvent(eid, username + ":" + " " + message);

	}

	@OnClose
	public void onClose(Session session) throws IOException 
	{
		String userName = users.get(session.getId());
		int eid = userToEventID.get(userName);
		broadcastToEvent(eid, userName + " has left the chat");

		users.remove(session.getId());
		sessionMap.remove(session.getId());
		userToEventID.remove(userName);
		chatEndpoints.remove(this);

	}

	private void sendAMessage(Session session, String message) 
	{
		try 
		{
			if (session.isOpen()) 
			{
				session.getBasicRemote().sendText(message);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void broadcastToEvent(int eid, String message) 
	{
		Iterator iter = userToEventID.entrySet().iterator();
		while (iter.hasNext()) 
		{
			Map.Entry cmp = (Map.Entry) iter.next();
			int tempID = (int) cmp.getValue();
			if (tempID == eid) 
			{
				String tempName = (String) cmp.getKey();
				Iterator iter2 = users.entrySet().iterator();
				while (iter2.hasNext()) 
				{
					Map.Entry cmp2 = (Map.Entry) iter2.next();
					String tempUser = (String) cmp2.getValue();
					if (tempName.equals(tempUser)) 
					{
						sendAMessage(sessionMap.get((String) cmp2.getKey()), message);

					}
				}
			}
		}
	}
}
