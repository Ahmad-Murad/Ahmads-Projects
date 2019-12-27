package iastate.edu.Event;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "event")
public class Event 
{
	public Event() 
	{
	}

	public Event(String eventName, String eventDescription, boolean privacy, String username) 
	{
		this.setUsername(username);
		this.setEventName(eventName);
		this.setPrivacy(privacy);
		this.setEventDescription(eventDescription);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@NotFound(action = NotFoundAction.IGNORE)
	private int eventId;

	@Column(name = "Event_Name")
	@NotFound(action = NotFoundAction.IGNORE)
	private String eventName;

	@Column(name = "Event_Description")
	@NotFound(action = NotFoundAction.IGNORE)
	private String eventDescription;

	@Column(name = "P_Status")
	@NotFound(action = NotFoundAction.IGNORE)
	private boolean privacy;

	@Column(name = "Username")
	@NotFound(action = NotFoundAction.IGNORE)
	private String username;
	@Column(name = "Event_Date")
	@NotFound(action = NotFoundAction.IGNORE)
	private Date cDate;
	@Column(name = "Location")
	@NotFound(action = NotFoundAction.IGNORE)
	private String location;

	@Column(name = "Locality")
	@NotFound(action = NotFoundAction.IGNORE)
	private String locality;

	@Column(name = "Lat")
	@NotFound(action = NotFoundAction.IGNORE)
	private double lat;

	@Column(name = "Log")
	@NotFound(action = NotFoundAction.IGNORE)
	private double log;

	public double getLat() 
	{
		return lat;
	}

	public double getLog() 
	{
		return log;
	}

	public String getLocality() 
	{
		return locality;
	}

	public void setLat(double lat) 
	{
		this.lat = lat;
	}

	public void setLocality(String locality) 
	{
		this.locality = locality;
	}

	public void setLog(double log) 
	{
		this.log = log;
	}

	public int getEventID() 
	{
		return this.eventId;
	}

	public void setId(int id) 
	{
		this.eventId = id;
	}

	public String getEventName() 
	{
		return this.eventName;
	}

	public void setEventName(String eventName) 
	{
		this.eventName = eventName;
	}

	public String getEventDescription() 
	{
		return this.eventDescription;
	}

	public void setEventDescription(String eventDescription) 
	{
		this.eventDescription = eventDescription;
	}

	public boolean isNew() 
	{
		return this.eventName.equals(null);
	}

	public boolean getPrivacy()
	{
		return this.privacy;
	}

	public void setPrivacy(boolean privacy)
	{
		this.privacy = privacy;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String uid) 
	{
		this.username = uid;
	}

	public Date getDate() 
	{
		return cDate;
	}

	public void setDate(Date cDate) 
	{
		this.cDate = cDate;
	}

	public Date getcDate() 
	{
		return cDate;
	}

	public boolean isPrivacy() 
	{
		return privacy;
	}

	public int getEventId()
	{
		return eventId;
	}

	public String getLocation() 
	{
		return location;
	}

	public void setcDate(Date cDate) 
	{
		this.cDate = cDate;
	}

	public void setEventId(int eventId) 
	{
		this.eventId = eventId;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
}
