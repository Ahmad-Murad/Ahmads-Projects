package iastate.edu.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@RestController
public class EventMemberController
{
	@Autowired
    EventMemberRepository eventMemberRepository;
	@Autowired
	EventRepository eventRepository;

	private final Logger logger = LoggerFactory.getLogger(EventMemberController.class);
	@RequestMapping(method = RequestMethod.POST, path = "/Event/Members/new",produces = { "application/json"})
	@GeneratedValue
	public void saveEventMembership(@RequestBody EventMember eventMember[])
	{
		for(int i=0; i < eventMember.length; i++)
		{
			eventMemberRepository.save(eventMember[i]);
		}
	}

// merge test
	@RequestMapping(method = RequestMethod.GET, path = "/Event/Members/{username}",produces = { "application/json"})
	public Event[] getParticipatingEvents(@PathVariable("username") String userName)
	{
		logger.info("Entered into Controller Layer");
		List<Integer> results = eventMemberRepository.getAllParticipatingEvents(userName);
		Event[] out = new Event[results.size()];
		for(int i = 0; i < results.size();i++)
		{
			out[i] = eventRepository.findByEid(results.get(i));
		}
		logger.info("Number of Records Fetched:" + results.size());
		return out;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/Event/Members/yeet",produces = { "application/json"})
	public List<EventMember>getAll()
	{
		List<EventMember> out = eventMemberRepository.findAll();
		return out;
	}
}
