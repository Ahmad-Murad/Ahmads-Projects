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
public class FriendsController {
    @Autowired
    FriendRepository friendRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/Friends/new",produces = { "application/json"})
    @GeneratedValue
    public void addFriend(@RequestBody Friend friend)
    {

        friendRepository.save(friend);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/Friends",produces = { "application/json"})
    public List<Friend> getAllFriends()
    {

        List<Friend> results = friendRepository.findAll();

        return results;
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/Friends/{userName1}/{userName2}",produces = { "application/json"})
    public void deleteFriends(@PathVariable("userName1") String username1, @PathVariable("userName2") String userName2) {
        friendRepository.deleteRow(username1,userName2);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/Friends/{userName1}/{userName2}",produces = { "application/json"})
    public String areFriends(@PathVariable("userName1") String username1, @PathVariable("userName2") String userName2)
    {
        int answer = friendRepository.getExists(username1,userName2);
        if(answer == 0)
            return "false";
        else
            return "true";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Friends/{userName}",produces = { "application/json"})
    public List<UserLite> friendsByUsername(@PathVariable("userName") String username)
    {
       ArrayList<String> userNames = (ArrayList) friendRepository.getFriendsByUsername(username);
       ArrayList<User> temp = new ArrayList<User>();
       ArrayList<UserLite> out = new ArrayList<UserLite>();
       UserLite Carrier;
       for(int i = 0; i < userNames.size(); i++)
       {
           temp.add(userRepository.getUserByUsername(userNames.get(i)));
           Carrier = new UserLite(temp.get(i).getUserName(),temp.get(i).getFirstName(), temp.get(i).getLastName());
           out.add(Carrier);
       }

        return out;
    }
}
