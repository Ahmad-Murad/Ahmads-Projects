package iastate.edu.Notification;

import iastate.edu.Friends.FriendRequest;
import iastate.edu.Friends.FriendRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.*;

@ServerEndpoint(value = "/Notification/{username}", configurator = CustomNotificationConfigurator.class)
@Component
public class NotificationServer 
{
	@Autowired
	FriendRequestRepository friendRequestRepository;

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException 
	{
		ArrayList<FriendRequest> out = (ArrayList<FriendRequest>) friendRequestRepository.getRequestsByTo(username);
		
		if (out.size() > 0) 
		{
			sendAMessage(session, "yes");
		} 
		else 
		{
			sendAMessage(session, "no");
		}
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
}
