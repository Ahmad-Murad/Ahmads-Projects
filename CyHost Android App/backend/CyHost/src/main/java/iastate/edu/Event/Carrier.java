package iastate.edu.Event;

public class Carrier 
{
	Event event;
	String uDate;
	String[] invitees;

	public Carrier(Event event, String uDate, String invitees[]) 
	{
		this.event = event;
		this.uDate = uDate;
		this.setInvitees(invitees);
	}

	public void setEvent(Event event) 
	{
		this.event = event;
	}

	public Event getEvent() 
	{
		return event;
	}

	public String getuDate()
	{
		return uDate;
	}

	public void setuDate(String uDate)
	{
		this.uDate = uDate;
	}

	public String[] getInvitees() 
	{
		return invitees;
	}

	public void setInvitees(String[] invitees)
	{
		this.invitees = new String[invitees.length];
		for (int i = 0; i < invitees.length; i++) 
		{
			this.invitees[i] = invitees[i];
		}
	}
}
