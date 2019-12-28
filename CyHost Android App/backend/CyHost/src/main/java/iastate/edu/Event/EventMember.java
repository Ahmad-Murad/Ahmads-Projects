package iastate.edu.Event;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(EventMember.class)
@Table(name = "event_members")

public class EventMember implements Serializable {
	@Id
	@Column(name = "ID")
	@NotFound(action = NotFoundAction.IGNORE)
	private int eventId;
	@Id
	@Column(name = "Username")
	@NotFound(action = NotFoundAction.IGNORE)
	private String userName;



	public int getEventID() {
		return this.eventId;
	}

	public void setId(int id) {
		this.eventId = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getEventID()).append("userName", this.getUserName()).toString();

	}
}
