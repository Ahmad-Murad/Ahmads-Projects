package iastate.edu.User;

/**
 * Additional object for a user with minimal detail
 */
public class UserLite 
{
	private String username;
	private String firstname;
	private String lastname;

	public String getFirstname() 
	{
		return firstname;
	}

	public String getLastname() 
	{
		return lastname;
	}

	public UserLite(String username, String firstname, String lastname) 
	{
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
}
