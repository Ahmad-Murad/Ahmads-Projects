package iastate.edu.Friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import iastate.edu.User.User;
import iastate.edu.User.UserLite;
import iastate.edu.User.UserRepository;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendRequestController {
    @Autowired
    FriendRepository friendRepository;

    @Autowired
    FriendRequestRepository friendRequestRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/Friend/Request/add",produces = { "application/json"})
    @GeneratedValue
    public void addFriendRequest(@RequestBody FriendRequest friendRequest)
    {
        friendRequestRepository.save(friendRequest);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/Friend/Request/{username}",produces = { "application/json"})
    public List<UserLite> getAllFriends(@PathVariable("username") String username)
    {
        ArrayList<FriendRequest> userNames = (ArrayList) friendRequestRepository.getRequestsByTo(username);
        ArrayList<User> temp = new ArrayList<User>();
        ArrayList<UserLite> out = new ArrayList<UserLite>();
        UserLite Carrier;
        for(int i = 0; i < userNames.size(); i++)
        {
            temp.add(userRepository.getUserByUsername(userNames.get(i).getFrom()));
            Carrier = new UserLite(temp.get(i).getUserName(),temp.get(i).getFirstName(), temp.get(i).getLastName());
            out.add(Carrier);
        }
        return out;
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/Friend/Request/Delete/{to}/{from}",produces = { "application/json"})
    public void removeRequest(@PathVariable("to") String to, @PathVariable("from") String from)
    {
        friendRequestRepository.deleteRequest(to,from);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/Friend/Request/Accept/{to}/{from}",produces = { "application/json"})
    public String acceptRequest(@PathVariable("to") String to, @PathVariable("from") String from)
    {
        friendRequestRepository.deleteRequest(to,from);
        if(friendRepository.getExists(to,from) == 0)
        {
            Friend carrier = new Friend();
            carrier.setFriend(from);
            carrier.setUserName(to);
            friendRepository.save(carrier);
            return "You are now friends with " + from;
        }
        else
        {
            return "Already Friends with "+ from;
        }
    }

}
