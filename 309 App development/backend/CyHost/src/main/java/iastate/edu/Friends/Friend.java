package iastate.edu.Friends;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "friend_list")
public class Friend {
    public Friend()
    {

    }
    public Friend(int id, String userName,String friend)
    {
        this.id = id;
        this.userName = userName;
        this.friend = friend;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendshipID")
    @NotFound(action = NotFoundAction.IGNORE)
    private int id;

    @Column(name = "Username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String userName;

    @Column(name = "Friend")
    @NotFound(action = NotFoundAction.IGNORE)
    private String friend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
