package iastate.edu.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.sql.Date;
import java.util.List;

@RestController
public class EventController 
{

	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventMemberRepository eventMemberRepository;

	private final Logger logger = LoggerFactory.getLogger(EventController.class);

	@RequestMapping(method = RequestMethod.POST, path = "/Event/new", produces = { "application/json" })
	@GeneratedValue
	public void saveEvent(@RequestBody Carrier car)
	{

		String correct = car.getuDate().substring(6, 10);
		correct += "-" + car.getuDate().substring(0, 5);

		Date newDate = Date.valueOf(correct);
		car.getEvent().setDate(newDate);
		eventRepository.save(car.getEvent());
		String userName = car.getEvent().getUsername();
		int eid = car.getEvent().getEventID();
		String[] inviteeHold = car.getInvitees();
		EventMember host = new EventMember();
		host.setId(eid);
		host.setUserName(userName);
		eventMemberRepository.save(host);
		for (int i = 0; i < inviteeHold.length; i++) 
		{
			EventMember holder = new EventMember();
			holder.setId(eid);
			holder.setUserName(inviteeHold[i]);
			eventMemberRepository.save(holder);
		}
		// return "Event" + car.getEvent().getEventName() + "saved";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/Event/{username}", produces = { "application/json" })
	public List<Event> getAllEvents(@PathVariable("username") String userName) 
	{
		logger.info("Entered into Controller Layer");
		List<Event> results = eventRepository.getAllUserEvents(userName);
		logger.info("Number of Records Fetched:" + results.size());
		return results;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/Event/delete/{ID}", produces = { "application/json" })
	public void deleteEvent(@PathVariable("ID") int ID) 
	{
		eventRepository.deleteEventEntry(ID);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/Event/all/{EventName}", produces = { "application/json" })
	public List<Event> getAllWithEventName(@PathVariable("EventName") String EventName)
	{
		List<Event> result = eventRepository.getAllEventsWithName(EventName);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/Event/Get/{id}", produces = { "application/json" })
	public Event findEventById(@PathVariable("id") int id) 
	{
		logger.info("Entered into Controller Layer");
		Event result = eventRepository.findByEid(id);
		return result;
	}
}
