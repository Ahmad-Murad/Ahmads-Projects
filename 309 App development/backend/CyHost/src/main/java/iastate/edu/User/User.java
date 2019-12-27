package iastate.edu.User;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Mimics user table within the database
 */
@Entity
@Table(name = "User")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@NotFound(action = NotFoundAction.IGNORE)
	private int userId;

	@Column(name = "username")
	@NotFound(action = NotFoundAction.IGNORE)
	private String userName;

	@Column(name = "Password")
	@NotFound(action = NotFoundAction.IGNORE)
	private String password;

	@Column(name = "First_Name")
	@NotFound(action = NotFoundAction.IGNORE)
	private String firstName;

	@Column(name = "Last_Name")
	@NotFound(action = NotFoundAction.IGNORE)
	private String lastName;

	@Column(name = "Email")
	@NotFound(action = NotFoundAction.IGNORE)
	private String email;

	@Column(name = "Zip_Code")
	@NotFound(action = NotFoundAction.IGNORE)
	private int zipCode;

	@Column(name = "Admin")
	@NotFound(action = NotFoundAction.IGNORE)
	private boolean isAdmin;

	public int getUserID() 
	{
		return this.userId;
	}

	public void setId(int id) 
	{
		this.userId = id;
	}

	public String getUserName() 
	{
		return this.userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public boolean isNew() 
	{
		return this.userName.equals(null);
	}

	public String getFirstName() 
	{
		return this.firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return this.lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getEmail() 
	{
		return this.email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public int getZipCode() 
	{
		return this.zipCode;
	}

	public void setZipCode(int zipCode) 
	{
		this.zipCode = zipCode;
	}

	public boolean isAdmin() 
	{
		return isAdmin;
	}

	public void setAdmin(boolean admin) 
	{
		isAdmin = admin;
	}
}
