package iastate.edu.Chat;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@IdClass(Chat.class)
@Table(name = "event_chat")

public class Chat 
{
	public Chat()
	{

	}
	public Chat(int chatId, String username, String message) 
	{
		this.chatId = chatId;
		this.username = username;
		this.message = message;
	}

	@Id
	@Column(name = "ID")
	@NotFound(action = NotFoundAction.IGNORE)
	private int chatId;

	@Id
	@Column(name = "Sender")
	@NotFound(action = NotFoundAction.IGNORE)
	private String username;

	@Id
	@Column(name = "Message")
	@NotFound(action = NotFoundAction.IGNORE)
	private String message;

	@Column(name = "Time")
	@NotFound(action = NotFoundAction.IGNORE)
	private Timestamp timestamp;

	public Timestamp getTimestamp() 
	{
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) 
	{
		this.timestamp = timestamp;
	}

	public int getChatID() 
	{
		return this.chatId;
	}

	public void setId(int id) 
	{
		this.chatId = id;
	}

	public String getUserName() 
	{
		return this.username;
	}

	public void setUserName(String userName) 
	{
		this.username = userName;
	}

	public String getMessage() 
	{
		return this.message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}
}
