package iastate.edu.Friends;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestID")
    @NotFound(action = NotFoundAction.IGNORE)
    private int id;

    @Column(name = "dstuser")
    @NotFound(action = NotFoundAction.IGNORE)
    private String to;

    @Column(name = "srcuser")
    @NotFound(action = NotFoundAction.IGNORE)
    private String from;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}


